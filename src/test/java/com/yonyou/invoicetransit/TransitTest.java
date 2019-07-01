package com.yonyou.invoicetransit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.yonyou.einvoice.common.transform.XMLTransformFacade;
import com.yonyou.invoicetransit.entity.transit.Business;
import com.yonyou.invoicetransit.entity.transit.ResponseCommonFpkj;
import com.yonyou.invoicetransit.entity.transit.ResultInvoice;
import com.yonyou.invoicetransit.simulation.ReturnInvoice;
import com.yonyou.invoicetransit.tools.RandomCharData;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransitTest {

  private static final Logger log=LoggerFactory.getLogger(TransitTest.class);

  /**
   * 数据模拟开票
   */
  @Test
  public void DataTest(){
    ResponseCommonFpkj responseCommonFpkj=new ResponseCommonFpkj("1133929406704685056");
    Business business=new Business();
    business.setResponseCommonFpkj(responseCommonFpkj);
    ResultInvoice resultInvoice=new ResultInvoice();
    resultInvoice.setBusiness(business);
   System.out.println(JSON.toJSONString(resultInvoice));

    //JSONObject retObj = new JSONObject(true);
    //Feature.OrderedField保证字段顺序
    System.out.println(XMLTransformFacade.getXMLStrFromJSONObject(JSONObject.parseObject(JSON.toJSONString(resultInvoice), Feature.OrderedField)));
  }

  /**
   * 数据模拟开票
   */
  @Test
  public void DataTest2(){
    ResponseCommonFpkj responseCommonFpkj=new ResponseCommonFpkj("1133929406704685056");
    Business business=new Business();
    business.setResponseCommonFpkj(responseCommonFpkj);
    ResultInvoice resultInvoice=new ResultInvoice();
    resultInvoice.setBusiness(business);
    System.out.println(JSON.toJSONString(resultInvoice));

    //JSONObject retObj = new JSONObject(true);
    //Feature.OrderedField保证字段顺序
    System.out.println(ReturnInvoice.toXML("1133929406704685056"));
  }

  /**
   * 测试生成随机数方法
   */
  @Test
  public void TestRandom(){

    log.info(RandomCharData.createFPDM());
    log.info(RandomCharData.createData(12));
  }

  @Test
  public void time(){
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    System.out.println(df.format(new Date()));
  }
}
