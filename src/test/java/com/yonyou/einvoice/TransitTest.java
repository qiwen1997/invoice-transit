package com.yonyou.einvoice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.github.pkuliuqiang.XMLTransformFacade;
import com.yonyou.einvoice.entity.MqMessage;
import com.yonyou.einvoice.entity.transit.Business;
import com.yonyou.einvoice.entity.transit.ResponseCommonFpkj;
import com.yonyou.einvoice.entity.transit.ResultInvoice;
import com.yonyou.einvoice.service.ReturnInvoice;
import com.yonyou.einvoice.service.StringToFile;
import com.yonyou.einvoice.util.JwtInnerUtils;
import com.yonyou.einvoice.util.RandomCharData;
import com.yonyou.einvoice.util.TimedTask;
import com.yonyou.einvoice.util.Tools;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvoiceTransitApplication.class)
@WebAppConfiguration
public class TransitTest {

  private static final Logger log=LoggerFactory.getLogger(TransitTest.class);

  @Autowired
  private ReturnInvoice returnInvoice;

  @Autowired
  private StringToFile stringToFile;
  /**
   * 数据模拟开票
   */
  @Test
  public void dataTest(){
    ResponseCommonFpkj responseCommonFpkj=new ResponseCommonFpkj("1133929406704685056");
    Business business=new Business();
    business.setResponseCommonFpkj(responseCommonFpkj);
    ResultInvoice resultInvoice=new ResultInvoice();
    resultInvoice.setBusiness(business);
   System.out.println(JSON.toJSONString(resultInvoice));

    //JSONObject retObj = new JSONObject(true);
    //Feature.OrderedField保证字段顺序
    System.out.println(XMLTransformFacade
        .getXMLStrFromJSONObject(JSONObject.parseObject(JSON.toJSONString(resultInvoice), Feature.OrderedField)));
  }

  /**
   * 数据模拟开票
   */
  @Test
  public void dataTest2(){
    ResponseCommonFpkj responseCommonFpkj=new ResponseCommonFpkj();
    responseCommonFpkj.setFpqqlsh("1133929406704685056");
    Business business=new Business();
    business.setResponseCommonFpkj(responseCommonFpkj);
    ResultInvoice resultInvoice=new ResultInvoice();
    resultInvoice.setBusiness(business);
    System.out.println(JSON.toJSONString(resultInvoice));

    //JSONObject retObj = new JSONObject(true);
    //Feature.OrderedField保证字段顺序
    System.out.println(returnInvoice.electronToXML("1133929406704685056"));
  }

  /**
   * 测试生成随机数方法
   */
  @Test
  public void testRandom(){

    log.info(RandomCharData.createFPDM());
    log.info(RandomCharData.createData(12));
    log.info(RandomCharData.createRandomCharData(10));
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
    //returnInvoice.paperToXML("1145893371939434496");
    System.out.println(returnInvoice.paperToXML("1145893371939434496"));
  }

  //测试Tools.getXML,保存电子发票文件
  @Test
  public void getXMLTest(){
    String messageStr="{\n"
        + "    \"context\": {\n"
        + "        \"corpid\": \"1937501a-b06f-4bf6-87d6-d5b515dacd26\", \n"
        + "        \"equipmentCode\": \"电票01\", \n"
        + "        \"id\": \"7404a795-3b60-49bf-a063-db038e9db1eb\", \n"
        + "        \"nsrsbh\": \"111222333456111\", \n"
        + "        \"type\": \"InvoiceApply\"\n"
        + "    }, \n"
        + "    \"data\": {\n"
        + "        \"data\": \"<?xml version=\\\"1.0\\\" encoding=\\\"GBK\\\"?>\n"
        + "<business comment=\\\"发票开具\\\" id=\\\"FPKJ\\\">\n"
        + "    <REQUEST_COMMON_FPKJ class=\\\"REQUEST_COMMON_FPKJ\\\">\n"
        + "        <COMMON_FPKJ_FPT class=\\\"COMMON_FPKJ_FPT\\\">\n"
        + "            <FPQQLSH>1146322763979206656</FPQQLSH>\n"
        + "            <BMB_BBH>12.0</BMB_BBH>\n"
        + "            <KPLX>0</KPLX>\n"
        + "            <CPYBZ/>\n"
        + "            <HSBZ>0</HSBZ>\n"
        + "            <XSF_NSRSBH>111222333456111</XSF_NSRSBH>\n"
        + "            <XSF_MC>测试1</XSF_MC>\n"
        + "            <XSF_DZDH>海淀122 89891111</XSF_DZDH>\n"
        + "            <XSF_YHZH>七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十 一</XSF_YHZH>\n"
        + "            <GMF_NSRSBH>91110000600001760P</GMF_NSRSBH>\n"
        + "            <GMF_MC>用友网络科技股份有限公司</GMF_MC>\n"
        + "            <GMF_DZDH>北京市海淀区北清路68号 010-86396688</GMF_DZDH>\n"
        + "            <GMF_YHZH>北京银行展览路支行 01090305800120108005804</GMF_YHZH>\n"
        + "            <KPR>测试test</KPR>\n"
        + "            <SKR/>\n"
        + "            <FHR/>\n"
        + "            <YFP_DM/>\n"
        + "            <YFP_HM/>\n"
        + "            <JSHJ>345.00</JSHJ>\n"
        + "            <HJJE>345.00</HJJE>\n"
        + "            <HJSE>0.00</HJSE>\n"
        + "            <BZ/>\n"
        + "        </COMMON_FPKJ_FPT>\n"
        + "        <COMMON_FPKJ_XMXXS class=\\\"COMMON_FPKJ_XMXX\\\" size=\\\"1\\\">\n"
        + "            <COMMON_FPKJ_XMXX>\n"
        + "                <FPHXZ>0</FPHXZ>\n"
        + "                <XMMC>豆油2</XMMC>\n"
        + "                <GGXH/>\n"
        + "                <SPBM>3060109010000000000</SPBM>\n"
        + "                <ZXBM>10</ZXBM>\n"
        + "                <YHZCBS>1</YHZCBS>\n"
        + "                <LSLBS>1</LSLBS>\n"
        + "                <ZZSTSGL>超税负3%即征即退</ZZSTSGL>\n"
        + "                <DW/>\n"
        + "                <XMSL>1</XMSL>\n"
        + "                <XMDJ>345</XMDJ>\n"
        + "                <SE>0.00</SE>\n"
        + "                <XMJE>345.00</XMJE>\n"
        + "                <SL>0.00</SL>\n"
        + "                <KCE/>\n"
        + "            </COMMON_FPKJ_XMXX>\n"
        + "        </COMMON_FPKJ_XMXXS>\n"
        + "    </REQUEST_COMMON_FPKJ>\n"
        + "</business>\n"
        + "\", \n"
        + "        \"fpjz\": \"0\", \n"
        + "        \"fplx\": \"1\", \n"
        + "        \"fpqqlsh\": \"1146322763979206656\", \n"
        + "        \"zsfs\": \"0\"\n"
        + "    }\n"
        + "}";
    String str=Tools.getXML(messageStr);
    System.out.println(str);

    String name="电子发票_"+Tools.getFpqqlshJSON(messageStr)+".xml";
    try {
      System.out.println(stringToFile.stringFile(str,"E:/yonyou/work/einput/"+name));
    } catch (Exception e) {
      e.printStackTrace();
    }
    String result= returnInvoice.electronToXML(Tools.getFpqqlshJSON(messageStr));
    System.out.println(result);

    String resultName="电子发票_"+Tools.getFpqqlshJSON(messageStr)+"_result.xml";
    try {
      System.out.println(stringToFile.stringFile(result,"E:/yonyou/work/eoutput/"+resultName));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //测试Tools.getXML,保存纸质发票文件
  @Test
  public void getXMLTest2(){
    String messageStr="{\n"
        + "    \"context\": {\n"
        + "        \"corpid\": \"1937501a-b06f-4bf6-87d6-d5b515dacd26\", \n"
        + "        \"equipmentCode\": \"awwq\", \n"
        + "        \"id\": \"e8d6cf7e-923c-40ff-896b-e7787d70a600\", \n"
        + "        \"nsrsbh\": \"111222333456111\", \n"
        + "        \"type\": \"InvoiceApply\"\n"
        + "    }, \n"
        + "    \"data\": {\n"
        + "        \"fpqqlsh\": \"1145893371939434496\", \n"
        + "        \"data\": \"<?xml version=\\\"1.0\\\" encoding=\\\"GBK\\\"?>\n"
        + "<Kp>\n"
        + "    <Version>2.0</Version>\n"
        + "    <Fpxx>\n"
        + "        <Zsl>1</Zsl>\n"
        + "        <Fpsj>\n"
        + "            <Fp>\n"
        + "                <Djh>1145893371939434496</Djh>\n"
        + "                <Gfmc>用友网络科技股份有限公司</Gfmc>\n"
        + "                <Gfsh>91110000600001760P</Gfsh>\n"
        + "                <Gfyhzh>北京银行展览路支行 01090305800120108005804</Gfyhzh>\n"
        + "                <Gfdzdh>北京市海淀区北清路68号 010-86396688</Gfdzdh>\n"
        + "                <Bz/>\n"
        + "                <Fhr/>\n"
        + "                <Skr/>\n"
        + "                <Spbmbbh>27.0</Spbmbbh>\n"
        + "                <Hsbz>0</Hsbz>\n"
        + "                <Spxx>\n"
        + "                    <Sph>\n"
        + "                        <Xh>1</Xh>\n"
        + "                        <Spmc>套刀</Spmc>\n"
        + "                        <Ggxh/>\n"
        + "                        <Jldw/>\n"
        + "                        <Spbm>1060512990000000000</Spbm>\n"
        + "                        <Qyspbm>30</Qyspbm>\n"
        + "                        <Syyhzcbz>0</Syyhzcbz>\n"
        + "                        <Lslbz/>\n"
        + "                        <Yhzcsm/>\n"
        + "                        <Kce/>\n"
        + "                        <Dj>37.6068376068</Dj>\n"
        + "                        <Sl>4</Sl>\n"
        + "                        <Je>150.427351</Je>\n"
        + "                        <Se>25.57</Se>\n"
        + "                        <Slv>0.17</Slv>\n"
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
        + "}\n";
    String str=Tools.getXML(messageStr);
    System.out.println(str);

    String name="纸质发票_"+Tools.getFpqqlshJSON(messageStr)+".xml";
    try {
      System.out.println(stringToFile.stringFile(str,"E:/yonyou/work/pinput/"+name));
    } catch (Exception e) {
      e.printStackTrace();
    }
    String result= returnInvoice.paperToXML(Tools.getFpqqlshJSON(messageStr));
    System.out.println(result);

    String resultName="纸质发票_"+Tools.getFpqqlshJSON(messageStr)+"_result.xml";
    try {
      System.out.println(stringToFile.stringFile(result,"E:/yonyou/work/poutput/"+resultName));
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  //测试签名
  @Test
  public void signTest(){
    System.out.println(JwtInnerUtils.sign(null));
  }

  //测试电子发票返回信息
  @Test
  public void eInvoiceTest(){
    String str="{\n"
        + "    \"context\": {\n"
        + "        \"corpid\": \"1937501a-b06f-4bf6-87d6-d5b515dacd26\",\n"
        + "        \"equipmentCode\": \"电票01\",\n"
        + "        \"id\": \"7630c721-ec91-4555-8429-4d1b694def91\",\n"
        + "        \"nsrsbh\": \"111222333456111\",\n"
        + "        \"type\": \"InvoiceApply\"\n"
        + "    },\n"
        + "    \"data\": {\n"
        + "        \"fpqqlsh\": \"1144497680717492224\",\n"
        + "        \"\n"
        + "data\": \"\n"
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
        + "\",\n"
        + "        \"fplx\": \"1\",\n"
        + "        \"fpjz\": \"0\",\n"
        + "        \"zsfs\": \"0\"\n"
        + "    }\n"
        + "}";
    MqMessage mqMessage = JSON.parseObject(str, MqMessage.class);
    try {
      System.out.println(returnInvoice.eInvoice(mqMessage));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //测试纸质发票返回信息
  @Test
  public void pInvoiceTest(){
    String str="{\n"
        + "    \"context\": {\n"
        + "        \"corpid\": \"1937501a-b06f-4bf6-87d6-d5b515dacd26\", \n"
        + "        \"equipmentCode\": \"awwq\", \n"
        + "        \"id\": \"5571c546-2aad-4b66-8a33-a6942ec4a42e\", \n"
        + "        \"nsrsbh\": \"111222333456111\", \n"
        + "        \"type\": \"InvoiceApply\"\n"
        + "    }, \n"
        + "    \"data\": {\n"
        + "        \"fpqqlsh\": \"1146956491727618048\", \n"
        + "        \"data\": \"<?xml version=\\\"1.0\\\" encoding=\\\"GBK\\\"?>\n"
        + "<Kp>\n"
        + "    <Version>2.0</Version>\n"
        + "    <Fpxx>\n"
        + "        <Zsl>1</Zsl>\n"
        + "        <Fpsj>\n"
        + "            <Fp>\n"
        + "                <Djh>1146956491727618048</Djh>\n"
        + "                <Gfmc>用友网络科技股份有限公司</Gfmc>\n"
        + "                <Gfsh>91110000600001760P</Gfsh>\n"
        + "                <Gfyhzh>北京银行展览路支行 01090305800120108005804</Gfyhzh>\n"
        + "                <Gfdzdh>北京市海淀区北清路68号 010-86396688</Gfdzdh>\n"
        + "                <Bz/>\n"
        + "                <Fhr/>\n"
        + "                <Skr/>\n"
        + "                <Spbmbbh>27.0</Spbmbbh>\n"
        + "                <Hsbz>0</Hsbz>\n"
        + "                <Spxx>\n"
        + "                    <Sph>\n"
        + "                        <Xh>1</Xh>\n"
        + "                        <Spmc>物业费</Spmc>\n"
        + "                        <Ggxh/>\n"
        + "                        <Jldw/>\n"
        + "                        <Spbm>3040801010000000000</Spbm>\n"
        + "                        <Qyspbm>30</Qyspbm>\n"
        + "                        <Syyhzcbz>0</Syyhzcbz>\n"
        + "                        <Lslbz/>\n"
        + "                        <Yhzcsm/>\n"
        + "                        <Kce/>\n"
        + "                        <Dj>43.6893203883</Dj>\n"
        + "                        <Sl>3</Sl>\n"
        + "                        <Je>131.067961</Je>\n"
        + "                        <Se>3.93</Se>\n"
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
    MqMessage mqMessage = JSON.parseObject(str, MqMessage.class);
    try {
      System.out.println(returnInvoice.pInvoice(mqMessage));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  @Autowired
  TimedTask timedTask;
  //测试超时文件删除方法
  @Test
  public void overTimeDeleteTest()throws Exception{

    String path="E:/yonyou/work/einput";
    File file=new File(path);
    timedTask.overTimeDelete(file);
  }

  @Test
  public void builderTest(){
    ResponseCommonFpkj responseCommonFpkj=ResponseCommonFpkj.create().setFpqqlsh("233333").build();
    System.out.println(JSON.toJSONString(responseCommonFpkj));
  }

}
