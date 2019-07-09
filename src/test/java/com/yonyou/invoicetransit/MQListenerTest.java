package com.yonyou.invoicetransit;

import com.alibaba.fastjson.JSON;
import com.yonyou.einvoice.InvoiceTransitApplication;
import com.yonyou.einvoice.entity.MqMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvoiceTransitApplication.class)
@WebAppConfiguration
public class MQListenerTest {

  @Value("${invoice.mq.queues}")
  private String queues;

  private static final String EXCHANGE_NAME = "iuap-direct-exchange";

  private static final String ROUTINGKEY_FLAG = "_KEY";

  @Autowired
  private RabbitTemplate rabbitTemplate;

  /**
   * 发送消息到队列，测试能否接收消息
   * 电子发票
   * str为原消息body解析后的json
   */
  @Test
  public void sendE(){

    String str="{\"context\":{\"corpid\":\"1937501a-b06f-4bf6-87d6-d5b515dacd26\",\"equipmentCode\":\"电票01\",\"id\":\"7630c721-ec91-4555-8429-4d1b694def91\",\"nsrsbh\":\"111222333456111\",\"type\":\"InvoiceApply\"},\"data\":{\"fpqqlsh\":\"1144497680717492224\",\"\n"
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

    this.rabbitTemplate.convertAndSend(EXCHANGE_NAME,queues+ROUTINGKEY_FLAG, JSON.parseObject(str, MqMessage.class));
  }

  /**
   * 发送消息到队列，测试能否接收消息
   * 电子发票
   * str为原mq消息json
   */
  @Test
  public void sendEX(){

    String str="{\n"
        + "    \"body\": \"eyJjb250ZXh0Ijp7ImNvcnBpZCI6IjE5Mzc1MDFhLWIwNmYtNGJmNi04N2Q2LWQ1YjUxNWRhY2QyNiIsImVxdWlwbWVudENvZGUiOiK158axMDEiLCJpZCI6IjNlYmNhY2NjLTgwYzgtNGY5OC04NDczLTIxODA2YzYyMjViYyIsIm5zcnNiaCI6IjExMTIyMjMzMzQ1NjExMSIsInR5cGUiOiJJbnZvaWNlQXBwbHkifSwiZGF0YSI6eyJkYXRhIjoiPD94bWwgdmVyc2lvbj1cIjEuMFwiIGVuY29kaW5nPVwiR0JLXCI/PlxuPGJ1c2luZXNzIGNvbW1lbnQ9XCK3osaxv6q+31wiIGlkPVwiRlBLSlwiPlxuICAgIDxSRVFVRVNUX0NPTU1PTl9GUEtKIGNsYXNzPVwiUkVRVUVTVF9DT01NT05fRlBLSlwiPlxuICAgICAgICA8Q09NTU9OX0ZQS0pfRlBUIGNsYXNzPVwiQ09NTU9OX0ZQS0pfRlBUXCI+XG4gICAgICAgICAgICA8RlBRUUxTSD4xMTQ2OTUzMDY1Mzc1NTE0NjI0PC9GUFFRTFNIPlxuICAgICAgICAgICAgPEJNQl9CQkg+MTIuMDwvQk1CX0JCSD5cbiAgICAgICAgICAgIDxLUExYPjA8L0tQTFg+XG4gICAgICAgICAgICA8Q1BZQlovPlxuICAgICAgICAgICAgPEhTQlo+MDwvSFNCWj5cbiAgICAgICAgICAgIDxYU0ZfTlNSU0JIPjExMTIyMjMzMzQ1NjExMTwvWFNGX05TUlNCSD5cbiAgICAgICAgICAgIDxYU0ZfTUM+suLK1DE8L1hTRl9NQz5cbiAgICAgICAgICAgIDxYU0ZfRFpESD66o7XtMTIyIDg5ODkxMTExPC9YU0ZfRFpESD5cbiAgICAgICAgICAgIDxYU0ZfWUhaSD7G37DLvsXKrtK7tv7I/cvEzuXB+cbfsMu+xcqu0ru2/sj9y8TO5cH5xt+wy77Fyq7Su7b+yP3LxM7lwfnG37DLvsXKrtK7tv7I/cvEzuXB+cbfsMu+xcquINK7PC9YU0ZfWUhaSD5cbiAgICAgICAgICAgIDxHTUZfTlNSU0JIPjkxMTEwMDAwNjAwMDAxNzYwUDwvR01GX05TUlNCSD5cbiAgICAgICAgICAgIDxHTUZfTUM+08PT0c34wue/xry8ucm33dPQz965q8u+PC9HTUZfTUM+XG4gICAgICAgICAgICA8R01GX0RaREg+sbG+qcrQuqO17cf4sbHH5cK3Nji6xSAwMTAtODYzOTY2ODg8L0dNRl9EWkRIPlxuICAgICAgICAgICAgPEdNRl9ZSFpIPrGxvqnS+NDQ1bnAwMK31qfQ0CAwMTA5MDMwNTgwMDEyMDEwODAwNTgwNDwvR01GX1lIWkg+XG4gICAgICAgICAgICA8S1BSPrLiytR0ZXN0PC9LUFI+XG4gICAgICAgICAgICA8U0tSLz5cbiAgICAgICAgICAgIDxGSFIvPlxuICAgICAgICAgICAgPFlGUF9ETS8+XG4gICAgICAgICAgICA8WUZQX0hNLz5cbiAgICAgICAgICAgIDxKU0hKPjU2LjAwPC9KU0hKPlxuICAgICAgICAgICAgPEhKSkU+NDkuNTY8L0hKSkU+XG4gICAgICAgICAgICA8SEpTRT42LjQ0PC9ISlNFPlxuICAgICAgICAgICAgPEJaLz5cbiAgICAgICAgPC9DT01NT05fRlBLSl9GUFQ+XG4gICAgICAgIDxDT01NT05fRlBLSl9YTVhYUyBjbGFzcz1cIkNPTU1PTl9GUEtKX1hNWFhcIiBzaXplPVwiMVwiPlxuICAgICAgICAgICAgPENPTU1PTl9GUEtKX1hNWFg+XG4gICAgICAgICAgICAgICAgPEZQSFhaPjA8L0ZQSFhaPlxuICAgICAgICAgICAgICAgIDxYTU1DPs3Fv+48L1hNTUM+XG4gICAgICAgICAgICAgICAgPEdHWEgvPlxuICAgICAgICAgICAgICAgIDxTUEJNPjMwNzAzMDEwMDAwMDAwMDAwMDA8L1NQQk0+XG4gICAgICAgICAgICAgICAgPFpYQk0+MTA8L1pYQk0+XG4gICAgICAgICAgICAgICAgPFlIWkNCUz4wPC9ZSFpDQlM+XG4gICAgICAgICAgICAgICAgPExTTEJTLz5cbiAgICAgICAgICAgICAgICA8WlpTVFNHTC8+XG4gICAgICAgICAgICAgICAgPERXLz5cbiAgICAgICAgICAgICAgICA8WE1TTD44PC9YTVNMPlxuICAgICAgICAgICAgICAgIDxYTURKPjYuMTk1PC9YTURKPlxuICAgICAgICAgICAgICAgIDxTRT42LjQ0PC9TRT5cbiAgICAgICAgICAgICAgICA8WE1KRT40OS41NjwvWE1KRT5cbiAgICAgICAgICAgICAgICA8U0w+MC4xMzwvU0w+XG4gICAgICAgICAgICAgICAgPEtDRS8+XG4gICAgICAgICAgICA8L0NPTU1PTl9GUEtKX1hNWFg+XG4gICAgICAgIDwvQ09NTU9OX0ZQS0pfWE1YWFM+XG4gICAgPC9SRVFVRVNUX0NPTU1PTl9GUEtKPlxuPC9idXNpbmVzcz5cbiIsImZwanoiOiIwIiwiZnBseCI6IjEiLCJmcHFxbHNoIjoiMTE0Njk1MzA2NTM3NTUxNDYyNCIsInpzZnMiOiIwIn19\", \n"
        + "    \"messageProperties\": {\n"
        + "        \"consumerQueue\": \"INVOICE-111222333456111-电票01\", \n"
        + "        \"consumerTag\": \"amq.ctag-KZWOUhwxJumBjbYk_9Nqcg\", \n"
        + "        \"contentEncoding\": \"GBK\", \n"
        + "        \"contentLength\": 0, \n"
        + "        \"contentType\": \"application/json\", \n"
        + "        \"deliveryTag\": 1, \n"
        + "        \"finalRetryForMessageWithNoId\": false, \n"
        + "        \"headers\": { }, \n"
        + "        \"messageId\": \"75185ae1-1d21-4263-a225-ce4180037a6a\", \n"
        + "        \"priority\": 0, \n"
        + "        \"publishSequenceNumber\": 0, \n"
        + "        \"receivedDeliveryMode\": \"PERSISTENT\", \n"
        + "        \"receivedExchange\": \"\", \n"
        + "        \"receivedRoutingKey\": \"INVOICE-111222333456111-电票01\", \n"
        + "        \"redelivered\": false\n"
        + "    }\n"
        + "}";

    Message message=JSON.parseObject(str,Message.class);
    this.rabbitTemplate.convertAndSend(EXCHANGE_NAME,queues+ROUTINGKEY_FLAG, message);
  }

  /**
   * 发送消息到队列，测试能否接收消息
   * 纸质发票
   * str为原消息body解析后的json
   */
  @Test
  public void sendP(){

    String str="{\n"
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

    this.rabbitTemplate.convertAndSend(EXCHANGE_NAME,queues+ROUTINGKEY_FLAG, JSON.parseObject(str, MqMessage.class));
  }

  /**
   * 发送消息到队列，测试能否接收消息
   * 纸质发票
   * str为原mq消息json
   */
  @Test
  public void sendPX(){

    String str="{\n"
        + "    \"body\": \"eyJjb250ZXh0Ijp7ImNvcnBpZCI6IjE5Mzc1MDFhLWIwNmYtNGJmNi04N2Q2LWQ1YjUxNWRhY2QyNiIsImVxdWlwbWVudENvZGUiOiJhd3dxIiwiaWQiOiI1NTcxYzU0Ni0yYWFkLTRiNjYtOGEzMy1hNjk0MmVjNGE0MmUiLCJuc3JzYmgiOiIxMTEyMjIzMzM0NTYxMTEiLCJ0eXBlIjoiSW52b2ljZUFwcGx5In0sImRhdGEiOnsiZGF0YSI6Ijw/eG1sIHZlcnNpb249XCIxLjBcIiBlbmNvZGluZz1cIkdCS1wiPz5cbjxLcD5cbiAgICA8VmVyc2lvbj4yLjA8L1ZlcnNpb24+XG4gICAgPEZweHg+XG4gICAgICAgIDxac2w+MTwvWnNsPlxuICAgICAgICA8RnBzaj5cbiAgICAgICAgICAgIDxGcD5cbiAgICAgICAgICAgICAgICA8RGpoPjExNDY5NTY0OTE3Mjc2MTgwNDg8L0RqaD5cbiAgICAgICAgICAgICAgICA8R2ZtYz7Tw9PRzfjC57/GvLy5ybfd09DP3rmry748L0dmbWM+XG4gICAgICAgICAgICAgICAgPEdmc2g+OTExMTAwMDA2MDAwMDE3NjBQPC9HZnNoPlxuICAgICAgICAgICAgICAgIDxHZnloemg+sbG+qdL40NDVucDAwrfWp9DQIDAxMDkwMzA1ODAwMTIwMTA4MDA1ODA0PC9HZnloemg+XG4gICAgICAgICAgICAgICAgPEdmZHpkaD6xsb6pytC6o7Xtx/ixscflwrc2OLrFIDAxMC04NjM5NjY4ODwvR2ZkemRoPlxuICAgICAgICAgICAgICAgIDxCei8+XG4gICAgICAgICAgICAgICAgPEZoci8+XG4gICAgICAgICAgICAgICAgPFNrci8+XG4gICAgICAgICAgICAgICAgPFNwYm1iYmg+MjcuMDwvU3BibWJiaD5cbiAgICAgICAgICAgICAgICA8SHNiej4wPC9Ic2J6PlxuICAgICAgICAgICAgICAgIDxTcHh4PlxuICAgICAgICAgICAgICAgICAgICA8U3BoPlxuICAgICAgICAgICAgICAgICAgICAgICAgPFhoPjE8L1hoPlxuICAgICAgICAgICAgICAgICAgICAgICAgPFNwbWM+zu/StbfRPC9TcG1jPlxuICAgICAgICAgICAgICAgICAgICAgICAgPEdneGgvPlxuICAgICAgICAgICAgICAgICAgICAgICAgPEpsZHcvPlxuICAgICAgICAgICAgICAgICAgICAgICAgPFNwYm0+MzA0MDgwMTAxMDAwMDAwMDAwMDwvU3BibT5cbiAgICAgICAgICAgICAgICAgICAgICAgIDxReXNwYm0+MzA8L1F5c3BibT5cbiAgICAgICAgICAgICAgICAgICAgICAgIDxTeXloemNiej4wPC9TeXloemNiej5cbiAgICAgICAgICAgICAgICAgICAgICAgIDxMc2xiei8+XG4gICAgICAgICAgICAgICAgICAgICAgICA8WWh6Y3NtLz5cbiAgICAgICAgICAgICAgICAgICAgICAgIDxLY2UvPlxuICAgICAgICAgICAgICAgICAgICAgICAgPERqPjQzLjY4OTMyMDM4ODM8L0RqPlxuICAgICAgICAgICAgICAgICAgICAgICAgPFNsPjM8L1NsPlxuICAgICAgICAgICAgICAgICAgICAgICAgPEplPjEzMS4wNjc5NjE8L0plPlxuICAgICAgICAgICAgICAgICAgICAgICAgPFNlPjMuOTM8L1NlPlxuICAgICAgICAgICAgICAgICAgICAgICAgPFNsdj4wLjAzPC9TbHY+XG4gICAgICAgICAgICAgICAgICAgIDwvU3BoPlxuICAgICAgICAgICAgICAgIDwvU3B4eD5cbiAgICAgICAgICAgICAgICA8S3ByPrLiytR0ZXN0PC9LcHI+XG4gICAgICAgICAgICAgICAgPFhmbWM+suLK1DE8L1hmbWM+XG4gICAgICAgICAgICAgICAgPFhmc2g+MTExMjIyMzMzNDU2MTExPC9YZnNoPlxuICAgICAgICAgICAgICAgIDxYZmR6ZGg+uqO17TEyMiA4OTg5MTExMTwvWGZkemRoPlxuICAgICAgICAgICAgICAgIDxYZnloemg+xt+wy77Fyq7Su7b+yP3LxM7lwfnG37DLvsXKrtK7tv7I/cvEzuXB+cbfsMu+xcqu0ru2/sj9y8TO5cH5xt+wy77Fyq7Su7b+yP3LxM7lwfnG37DLvsXKriDSuzwvWGZ5aHpoPlxuICAgICAgICAgICAgPC9GcD5cbiAgICAgICAgPC9GcHNqPlxuICAgIDwvRnB4eD5cbjwvS3A+XG4iLCJmcGp6IjoiMSIsImZwbHgiOiIzIiwiZnBxcWxzaCI6IjExNDY5NTY0OTE3Mjc2MTgwNDgiLCJ6c2ZzIjoiMCJ9fQ==\", \n"
        + "    \"messageProperties\": {\n"
        + "        \"consumerQueue\": \"INVOICE-111222333456111-awwq\", \n"
        + "        \"consumerTag\": \"amq.ctag-i9jNdyxGgod4lpQFGkaNtw\", \n"
        + "        \"contentEncoding\": \"GBK\", \n"
        + "        \"contentLength\": 0, \n"
        + "        \"contentType\": \"application/json\", \n"
        + "        \"deliveryTag\": 1, \n"
        + "        \"finalRetryForMessageWithNoId\": false, \n"
        + "        \"headers\": { }, \n"
        + "        \"messageId\": \"2b22bfa5-e185-4b0e-ba34-f8f02c5aac1d\", \n"
        + "        \"priority\": 0, \n"
        + "        \"publishSequenceNumber\": 0, \n"
        + "        \"receivedDeliveryMode\": \"PERSISTENT\", \n"
        + "        \"receivedExchange\": \"\", \n"
        + "        \"receivedRoutingKey\": \"INVOICE-111222333456111-awwq\", \n"
        + "        \"redelivered\": false\n"
        + "    }\n"
        + "}";

    Message message=JSON.parseObject(str,Message.class);
    this.rabbitTemplate.convertAndSend(EXCHANGE_NAME,queues+ROUTINGKEY_FLAG, message);

    //Assert.assertEq
  }
}
