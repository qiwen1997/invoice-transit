package com.yonyou.invoicetransit.simulation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.github.pkuliuqiang.XMLTransformFacade;
import com.yonyou.invoicetransit.entity.MqMessage;
import com.yonyou.invoicetransit.entity.MqResult;
import com.yonyou.invoicetransit.entity.transit.Business;
import com.yonyou.invoicetransit.entity.transit.ResponseCommonFpkj;
import com.yonyou.invoicetransit.entity.transit.ResultInvoice;
import com.yonyou.invoicetransit.tools.RandomCharData;
import com.yonyou.invoicetransit.tools.ReviseXml;
import com.yonyou.invoicetransit.tools.Tools;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


/**
 * 生成返回结果
 */
public class ReturnInvoice {

  //电子发票生成context
  public static String toXML(String fpqqlsh){
    ResponseCommonFpkj responseCommonFpkj=new ResponseCommonFpkj(fpqqlsh);
    Business business=new Business();
    business.setResponseCommonFpkj(responseCommonFpkj);
    ResultInvoice resultInvoice=new ResultInvoice();
    resultInvoice.setBusiness(business);
    //Feature.OrderedField保证字段顺序
    String s= XMLTransformFacade
        .getXMLStrFromJSONObject
            (JSONObject.parseObject(JSON.toJSONString(resultInvoice), Feature.OrderedField));
    return ReviseXml.changeEncoding(s);
  }

  //纸质发票生成context
  public static String paperToXML(String fpqqlsh){
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String kprq=df.format(new Date());
    String fpdm= RandomCharData.createFPDM();
    String fphm=RandomCharData.createData(8);
    return "["+kprq+"]"+" 单据号:"+fpqqlsh+",开具结果:1,对应发票信息:增值税普通发票,"+fpdm+","+fphm;
  }

  //生成电子发票需要返回的实体字符串
  public static String eInvoice(MqMessage mqMessage) throws Exception{

    MqResult mqResult=new MqResult(ReturnInvoice.toXML(Tools.getFpqqlshJSON(JSON.toJSONString(mqMessage))),
        "AutoEinvoice");
    MqMessage<String> resultMqMessage=new MqMessage<String>();
    resultMqMessage.getContext().setType("TaxEquipmentResult");

    String randomId=RandomCharData.createRandomCharData(8)+"-"+
        RandomCharData.createRandomCharData(4)+"-"+
        RandomCharData.createRandomCharData(4)+"-"+
        RandomCharData.createRandomCharData(4)+"-"+
        RandomCharData.createRandomCharData(12);
    resultMqMessage.getContext().setId(randomId);
    resultMqMessage.getContext().setCorpid(mqMessage.getContext().getCorpid());
    resultMqMessage.getContext().setNsrsbh(mqMessage.getContext().getNsrsbh());
    resultMqMessage.getContext().setEquipmentCode(mqMessage.getContext().getEquipmentCode());
    //resultMqMessage.setContext(mqMessage.getContext());
    resultMqMessage.setData(ReturnInvoice.convertToBase64MqMessage(mqResult));
    System.out.println(JSON.toJSONString(resultMqMessage));
    return JSON.toJSONString(resultMqMessage);
  }

  //生成纸质发票需要返回的实体字符串
  public static String pInvoice(MqMessage mqMessage) throws Exception{

    MqResult mqResult=new MqResult(ReturnInvoice.paperToXML(Tools.getFpqqlshJSON(JSON.toJSONString(mqMessage))),
        "AutoPaperInvoice");
    MqMessage<String> resultMqMessage=new MqMessage<String>();
    resultMqMessage.getContext().setType("TaxEquipmentResult");

    String randomId=RandomCharData.createRandomCharData(8)+"-"+
        RandomCharData.createRandomCharData(4)+"-"+
        RandomCharData.createRandomCharData(4)+"-"+
        RandomCharData.createRandomCharData(4)+"-"+
        RandomCharData.createRandomCharData(12);
    resultMqMessage.getContext().setId(randomId);
    resultMqMessage.getContext().setCorpid(mqMessage.getContext().getCorpid());
    resultMqMessage.getContext().setNsrsbh(mqMessage.getContext().getNsrsbh());
    resultMqMessage.getContext().setEquipmentCode(mqMessage.getContext().getEquipmentCode());
    resultMqMessage.setData(ReturnInvoice.convertToBase64MqMessage(mqResult));
    return JSON.toJSONString(resultMqMessage);
  }

  //把data部分转换成base64
  public static String convertToBase64MqMessage(MqResult mqResult)throws Exception{
    String result=JSON.toJSONString(mqResult);
    String base64= Base64.getEncoder().encodeToString(result.getBytes("GBK"));
    return base64;
  }

  //调用助手消息回传接口
  public static String callBack(String json) throws Exception{
//    System.setProperty("http.proxyHost", "localhost");
//    System.setProperty("http.proxyPort", "8888");
//    System.setProperty("https.proxyHost", "localhost");
//    System.setProperty("https.proxyPort", "8888");

    String url="http://192.168.52.80/clientmessage/privateapi/clientapp/v2/message";
//    HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
//    DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
//
//    HttpClient httpClient = HttpClients.custom().setRoutePlanner(routePlanner).build();

    HttpClient httpClient = HttpClients.custom().build();
    HttpPost httpPost = new HttpPost(url);

    httpPost.addHeader("sign", JwtInnerUtils.sign(null));
    httpPost.addHeader("Content-Type", "application/json");
    httpPost.addHeader("Accept","application/json, application/xml, text/json, text/x-json, text/javascript, text/xml");
    httpPost.setEntity(new StringEntity(json,"UTF-8"));
    HttpResponse response = httpClient.execute(httpPost);
    String result = "";
    if (response != null) {
      HttpEntity resEntity = response.getEntity();
      if (resEntity != null) {
        result = EntityUtils.toString(resEntity, "UTF-8");
      }
    }
    return result;
  }

}
