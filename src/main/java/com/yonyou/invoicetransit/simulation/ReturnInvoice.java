package com.yonyou.invoicetransit.simulation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.yonyou.einvoice.common.transform.XMLTransformFacade;
import com.yonyou.invoicetransit.entity.transit.Business;
import com.yonyou.invoicetransit.entity.transit.ResponseCommonFpkj;
import com.yonyou.invoicetransit.entity.transit.ResultInvoice;

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
}
