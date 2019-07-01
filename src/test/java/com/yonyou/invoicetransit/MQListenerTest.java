package com.yonyou.invoicetransit;

import com.alibaba.fastjson.JSON;
import com.yonyou.invoicetransit.entity.MqInvoiceApply;
import com.yonyou.invoicetransit.entity.MqMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MQListenerTest {

  @Value("${invoice.mq.queues}")
  private String queues;

  private static final String EXCHANGE_NAME = "iuap-direct-exchange";

  private static final String ROUTINGKEY_FLAG = "_KEY";

  @Autowired
  private RabbitTemplate rabbitTemplate;

  /**
   * 发送消息到队列，测试能否接收消息
   */
  @Test
  public void send(){

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
}
