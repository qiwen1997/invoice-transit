package com.yonyou.invoicetransit.simulation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.yonyou.einvoice.common.transform.XMLTransformFacade;
import com.yonyou.invoicetransit.entity.transit.Business;
import com.yonyou.invoicetransit.entity.transit.ResponseCommonFpkj;
import com.yonyou.invoicetransit.entity.transit.ResultInvoice;
import com.yonyou.invoicetransit.tools.RandomCharData;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成返回结果
 */
public class ReturnInvoice {

  public static String toXML(String fpqqlsh){
    ResponseCommonFpkj responseCommonFpkj=new ResponseCommonFpkj(fpqqlsh);
    Business business=new Business();
    business.setResponseCommonFpkj(responseCommonFpkj);
    ResultInvoice resultInvoice=new ResultInvoice();
    resultInvoice.setBusiness(business);
    //Feature.OrderedField保证字段顺序
    return XMLTransformFacade
        .getXMLStrFromJSONObject
            (JSONObject.parseObject(JSON.toJSONString(resultInvoice), Feature.OrderedField));
  }

  public static String paperToXML(String fpqqlsh){
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String kprq=df.format(new Date());
    String fpdm= RandomCharData.createFPDM();
    String fphm=RandomCharData.createData(8);
    return "["+kprq+"]"+" 单据号:"+fpqqlsh+",开具结果:1,对应发票信息:增值税普通发票,"+fpdm+","+fphm;
  }
}
