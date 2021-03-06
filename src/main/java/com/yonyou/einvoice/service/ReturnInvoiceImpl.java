package com.yonyou.einvoice.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.github.pkuliuqiang.XMLTransformFacade;
import com.yonyou.einvoice.entity.MqResult;
import com.yonyou.einvoice.entity.transit.Business;
import com.yonyou.einvoice.entity.transit.ResponseCommonFpkj;
import com.yonyou.einvoice.entity.transit.ResultInvoice;
import com.yonyou.einvoice.entity.MqMessage;
import com.yonyou.einvoice.util.JwtInnerUtils;
import com.yonyou.einvoice.util.RandomCharData;
import com.yonyou.einvoice.util.ReviseXml;
import com.yonyou.einvoice.util.Tools;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 *发票相关业务功能实现
 * created by qiwen on 2019/7/19
 */
@Service
public class ReturnInvoiceImpl implements ReturnInvoice{

  @Autowired
  private ReturnInvoice returnInvoice;

  @Autowired
  private RestTemplate restTemplate;

  //电子发票生成context
  @Override
  public  String electronToXML(String fpqqlsh){
    ResponseCommonFpkj responseCommonFpkj=ResponseCommonFpkj.create().setFpqqlsh(fpqqlsh).build();
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
  @Override
  public  String paperToXML(String fpqqlsh){
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String kprq=df.format(new Date());
    String fpdm= RandomCharData.createFPDM();
    String fphm=RandomCharData.createData(8);
    return "["+kprq+"]"+" 单据号:"+fpqqlsh+",开具结果:1,对应发票信息:增值税普通发票,"+fpdm+","+fphm;
  }


  //生成电子发票需要返回的实体字符串
  @Override
  public  String eInvoice(MqMessage mqMessage) throws Exception{

    MqResult mqResult=new MqResult(returnInvoice.electronToXML(Tools.getFpqqlshJSON(JSON.toJSONString(mqMessage))),
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
    resultMqMessage.setData(returnInvoice.convertToBase64MqMessage(mqResult));
    return JSON.toJSONString(resultMqMessage);
  }

  //生成纸质发票需要返回的实体字符串
  @Override
  public  String pInvoice(MqMessage mqMessage) throws Exception{

    MqResult mqResult=new MqResult(returnInvoice.paperToXML(Tools.getFpqqlshJSON(JSON.toJSONString(mqMessage))),
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
    resultMqMessage.setData(returnInvoice.convertToBase64MqMessage(mqResult));
    return JSON.toJSONString(resultMqMessage);
  }

  //把data部分转换成base64
  @Override
  public  String convertToBase64MqMessage(MqResult mqResult)throws Exception{
    String result=JSON.toJSONString(mqResult);
    String base64= Base64.getEncoder().encodeToString(result.getBytes("GBK"));
    return base64;
  }

  //调用助手消息回传接口
  @Override
  public String callBack(String json) throws Exception {

    String url="http://192.168.52.80/clientmessage/privateapi/clientapp/v2/message";
//    String url="https://intellid.diwork.com/intellid/frontapi/common/listTaxPunishment?keyword="+
//        "用友网络科技股份有限公司";

    HttpHeaders httpHeaders = new HttpHeaders();

    httpHeaders.add("sign", JwtInnerUtils.sign(null));
    httpHeaders.add("Content-Type", "application/json");
    httpHeaders.add("Accept","application/json, application/xml, text/json, text/x-json, text/javascript, text/xml");
    HttpEntity<String> httpEntity=new HttpEntity<>(json,httpHeaders);
    //HttpEntity<String> httpEntity=new HttpEntity<>(httpHeaders);
    ResponseEntity<JSONObject> responseEntity=restTemplate.postForEntity(url,httpEntity,JSONObject.class);
    return responseEntity.getBody().toJSONString();
  }

  /**
   * 重大税收违法案件信息接口调用
   * @param keyword （必填）企业名称或税号
   * @param pageSize （非必填）页面大小
   * @param pageNumber（非必填）页面号码
   * @return
   */
  @Override
  public String listTaxPunishmentCall(String keyword,String pageSize,String pageNumber){
    String url="http://172.20.52.165:9090/rest/common/listTaxPunishment?keyword="+
        keyword;
    if(!"".equals(pageSize)&&pageSize!=null){
      url=url+"&pageSize="+pageSize;
    }
    if(!"".equals(pageNumber)&&pageNumber!=null){
      url=url+"&pageNumber="+pageNumber;
    }
    ResponseEntity<String> responseEntity=restTemplate.getForEntity(url,String.class);
    return responseEntity.getBody();
  }

  @Override
  public String listTaxPunishmentCall(String keyword, String pageNumber) {
    String url="http://172.20.52.165:9090/rest/common/listTaxPunishment?keyword="+
        keyword;
    if(!"".equals(pageNumber)&&pageNumber!=null){
      url=url+"&pageNumber="+pageNumber;
    }
    ResponseEntity<String> responseEntity=restTemplate.getForEntity(url,String.class);
    return responseEntity.getBody();
  }

  @Override
  public String listTaxPunishmentCall(String keyword) {
    String url="http://172.20.52.165:9090/rest/common/listTaxPunishment?keyword="+
        keyword;
    ResponseEntity<String> responseEntity=restTemplate.getForEntity(url,String.class);
    return responseEntity.getBody();
  }
}
