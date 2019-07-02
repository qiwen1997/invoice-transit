package com.yonyou.invoicetransit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.yonyou.einvoice.common.transform.XMLTransformFacade;
import com.yonyou.invoicetransit.entity.transit.Business;
import com.yonyou.invoicetransit.entity.transit.ResponseCommonFpkj;
import com.yonyou.invoicetransit.entity.transit.ResultInvoice;
import com.yonyou.invoicetransit.simulation.ReturnInvoice;
import com.yonyou.invoicetransit.tools.RandomCharData;
import com.yonyou.invoicetransit.tools.Tools;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
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
    ResponseCommonFpkj responseCommonFpkj=new ResponseCommonFpkj();
    responseCommonFpkj.setFpqqlsh("1133929406704685056");
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

  @Test
  public void getFpqqlshTest(){
    //System.out.println(Tools.getFpqqlshJSON("{\"context\":{\"corpid\":\"1937501a-b06f-4bf6-87d6-d5b515dacd26\",\"equipmentCode\":\"awwq\",\"id\":\"01c03793-88b2-4815-a4e7-301449d46a8f\",\"nsrsbh\":\"111222333456111\",\"type\":\"InvoiceApply\"},\"data\":{\"fpqqlsh\":\"1145872944487772160\",\"data\":\"<?xml version=\\\"1.0\\\" encoding=\\\"GBK\\\"?>\\n<Kp>\\n    <Version>2.0</Version>\\n    <Fpxx>\\n        <Zsl>1</Zsl>\\n        <Fpsj>\\n            <Fp>\\n                <Djh>1145872944487772160</Djh>\\n                <Gfmc>测试水利电力税率升级</Gfmc>\\n                <Gfsh>333333333333333</Gfsh>\\n                <Gfyhzh>是的噶333333</Gfyhzh>\\n                <Gfdzdh>水电费费3323233</Gfdzdh>\\n                <Bz/>\\n                <Fhr/>\\n                <Skr/>\\n                <Spbmbbh>27.0</Spbmbbh>\\n                <Hsbz>0</Hsbz>\\n                <Spxx>\\n                    <Sph>\\n                        <Xh>1</Xh>\\n                        <Spmc>季节加价</Spmc>\\n                        <Ggxh/>\\n                        <Jldw/>\\n                        <Spbm>1100301010000000000</Spbm>\\n                        <Qyspbm>30</Qyspbm>\\n                        <Syyhzcbz>0</Syyhzcbz>\\n                        <Lslbz/>\\n                        <Yhzcsm/>\\n                        <Kce/>\\n                        <Dj>3.8834951456</Dj>\\n                        <Sl>5</Sl>\\n                        <Je>19.417476</Je>\\n                        <Se>0.58</Se>\\n                        <Slv>0.03</Slv>\\n                    </Sph>\\n                </Spxx>\\n                <Kpr>测试test</Kpr>\\n                <Xfmc>测试1</Xfmc>\\n                <Xfsh>111222333456111</Xfsh>\\n                <Xfdzdh>海淀122 89891111</Xfdzdh>\\n                <Xfyhzh>七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十 一</Xfyhzh>\\n            </Fp>\\n        </Fpsj>\\n    </Fpxx>\\n</Kp>\\n\",\"fplx\":\"3\",\"fpjz\":\"1\",\"zsfs\":\"0\"}}\n"));
    String json="{\"context\":{\"corpid\":\"1937501a-b06f-4bf6-87d6-d5b515dacd26\",\"equipmentCode\":\"电票01\",\"id\":\"7630c721-ec91-4555-8429-4d1b694def91\",\"nsrsbh\":\"111222333456111\",\"type\":\"InvoiceApply\"},\"data\":{\"fpqqlsh\":\"1144497680717492224\",\"\n"
        + "data\":\"\n"
        + "<?xml version=\\\"1.0\\\" encoding=\\\"GBK\\\"?>\\n\n"
        + "<business comment=\\\"发票开具\\\" id=\\\"FPKJ\\\">\\n\n"
        + "    <REQUEST_COMMON_FPKJ class=\\\"REQUEST_COMMON_FPKJ\\\">\\n\n"
        + "        <COMMON_FPKJ_FPT class=\\\"COMMON_FPKJ_FPT\\\">\\n\n"
        + "            <FPQQLSH>1144497680717492224</FPQQLSH>\\n\n"
        + "            <BMB_BBH>12.0</BMB_BBH>\\n\n"
        + "            <KPLX>0</KPLX>\\n\n"
        + "            <CPYBZ/>\\n   \n"
        + "            <HSBZ>0</HSBZ>\\n \n"
        + "           <XSF_NSRSBH>111222333456111</XSF_NSRSBH>\\n\n"
        + "            <XSF_MC>测试1</XSF_MC>\\n\n"
        + "            <XSF_DZDH>海淀122 89891111</XSF_DZDH>\\n\n"
        + "            <XSF_YHZH>七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十 一</XSF_YHZH>\\n\n"
        + "            <GMF_NSRSBH>91110000600001760P</GMF_NSRSBH>\\n\n"
        + "            <GMF_MC>用友网络科技股份有限公司</GMF_MC>\\n \n"
        + "           <GMF_DZDH>北京市海淀区北清路68号 010-86396688</GMF_DZDH>\\n \n"
        + "           <GMF_YHZH>北京银行展览路支行 01090305800120108005804</GMF_YHZH>\\n \n"
        + "           <KPR>测试test</KPR>\\n\n"
        + "            <SKR/>\\n\n"
        + "            <FHR/>\\n \n"
        + "           <YFP_DM/>\\n \n"
        + "           <YFP_HM/>\\n \n"
        + "           <JSHJ>49.00</JSHJ>\\n \n"
        + "           <HJJE>49.00</HJJE>\\n\n"
        + "            <HJSE>0.00</HJSE>\\n \n"
        + "           <BZ/>\\n \n"
        + "       </COMMON_FPKJ_FPT>\\n \n"
        + "       <COMMON_FPKJ_XMXXS class=\\\"COMMON_FPKJ_XMXX\\\" size=\\\"1\\\">\\n \n"
        + "           <COMMON_FPKJ_XMXX>\\n \n"
        + "               <FPHXZ>0</FPHXZ>\\n \n"
        + "               <XMMC>污水处理费</XMMC>\\n\n"
        + "                <GGXH/>\\n \n"
        + "               <SPBM>2010300000000000000</SPBM>\\n \n"
        + "               <ZXBM>10</ZXBM>\\n \n"
        + "               <YHZCBS>0</YHZCBS>\\n\n"
        + "                <LSLBS/>\\n \n"
        + "               <ZZSTSGL/>\\n \n"
        + "               <DW/>\\n\n"
        + "                <XMSL>7</XMSL>\\n \n"
        + "               <XMDJ>7</XMDJ>\\n \n"
        + "               <SE>0.00</SE>\\n \n"
        + "               <XMJE>49.00</XMJE>\\n\n"
        + "                <SL>0.00</SL>\\n \n"
        + "               <KCE/>\\n\n"
        + "            </COMMON_FPKJ_XMXX>\\n\n"
        + "        </COMMON_FPKJ_XMXXS>\\n\n"
        + "    </REQUEST_COMMON_FPKJ>\\n\n"
        + "</business>\\n\n"
        + "\",\"fplx\":\"1\",\"fpjz\":\"0\",\"zsfs\":\"0\"}}";

    String json2="{\n"
        + "    \"context\": {\n"
        + "        \"corpid\": \"1937501a-b06f-4bf6-87d6-d5b515dacd26\", \n"
        + "        \"equipmentCode\": \"awwq\", \n"
        + "        \"id\": \"01c03793-88b2-4815-a4e7-301449d46a8f\", \n"
        + "        \"nsrsbh\": \"111222333456111\", \n"
        + "        \"type\": \"InvoiceApply\"\n"
        + "    }, \n"
        + "    \"data\": {\n"
        + "        \"fpqqlsh\": \"1145872944487772160\", \n"
        + "        \"data\": \"<?xml version=\\\"1.0\\\" encoding=\\\"GBK\\\"?>\n"
        + "<Kp>\n"
        + "    <Version>2.0</Version>\n"
        + "    <Fpxx>\n"
        + "        <Zsl>1</Zsl>\n"
        + "        <Fpsj>\n"
        + "            <Fp>\n"
        + "                <Djh>1145872944487772160</Djh>\n"
        + "                <Gfmc>测试水利电力税率升级</Gfmc>\n"
        + "                <Gfsh>333333333333333</Gfsh>\n"
        + "                <Gfyhzh>是的噶333333</Gfyhzh>\n"
        + "                <Gfdzdh>水电费费3323233</Gfdzdh>\n"
        + "                <Bz/>\n"
        + "                <Fhr/>\n"
        + "                <Skr/>\n"
        + "                <Spbmbbh>27.0</Spbmbbh>\n"
        + "                <Hsbz>0</Hsbz>\n"
        + "                <Spxx>\n"
        + "                    <Sph>\n"
        + "                        <Xh>1</Xh>\n"
        + "                        <Spmc>季节加价</Spmc>\n"
        + "                        <Ggxh/>\n"
        + "                        <Jldw/>\n"
        + "                        <Spbm>1100301010000000000</Spbm>\n"
        + "                        <Qyspbm>30</Qyspbm>\n"
        + "                        <Syyhzcbz>0</Syyhzcbz>\n"
        + "                        <Lslbz/>\n"
        + "                        <Yhzcsm/>\n"
        + "                        <Kce/>\n"
        + "                        <Dj>3.8834951456</Dj>\n"
        + "                        <Sl>5</Sl>\n"
        + "                        <Je>19.417476</Je>\n"
        + "                        <Se>0.58</Se>\n"
        + "                        <Slv>0.03</Slv>\n"
        + "                    </Sph>\n"
        + "                </Spxx>\n"
        + "                <Kpr>测试test</Kpr>\n"
        + "                <Xfmc>测试1</Xfmc>\n"
        + "                <Xfsh>111222333456111</Xfsh>\n"
        + "                <Xfdzdh>海淀122 89891111</Xfdzdh>\n"
        + "                <Xfyhzh>七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十 一</Xfyhzh>\n"
        + "            </Fp>\n"
        + "        </Fpsj>\n"
        + "    </Fpxx>\n"
        + "</Kp>\n"
        + "\", \n"
        + "        \"fplx\": \"3\", \n"
        + "        \"fpjz\": \"1\", \n"
        + "        \"zsfs\": \"0\"\n"
        + "    }\n"
        + "}";
    System.out.println(Tools.getFpqqlshJSON(json2));
  }

  @Test
  public void paperTest(){
    //ReturnInvoice.paperToXML("1145893371939434496");
    System.out.println(ReturnInvoice.paperToXML("1145893371939434496"));
  }

}
