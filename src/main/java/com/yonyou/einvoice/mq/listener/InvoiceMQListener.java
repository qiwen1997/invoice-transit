package com.yonyou.einvoice.mq.listener;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.rabbitmq.client.Channel;
import com.yonyou.einvoice.service.ReturnInvoice;
import com.yonyou.einvoice.service.StringToFile;
import com.yonyou.einvoice.entity.MqMessage;
import com.yonyou.einvoice.exception.BusinessRuntimeException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by yangchuanhua on 2017/11/28.
 */
@Component
public class InvoiceMQListener implements ChannelAwareMessageListener {

  @Autowired
  private ReturnInvoice returnInvoice;

  @Autowired
  private StringToFile stringToFile;

  private static final Logger logger = LoggerFactory.getLogger(InvoiceMQListener.class);
  private static final String CHARSET_GBK = "gbk";
  private static final String MESSAGE_PREFIX = "MQListener:";

  @Value("${invoice.save.einput}")
  private String eInPut;

  @Value("${invoice.save.eoutput}")
  private String eOutPut;

  @Value("${invoice.save.pinput}")
  private String pInPut;

  @Value("${invoice.save.poutput}")
  private String pOutPut;
  //LoadingCache本地缓存
  //CacheBuilder.maximumSize(long)：这个方法规定缓存项的数目不超过固定值
  // (其实你可以理解为一个Map的最大容量)，尝试回收最近没有使用或总体上很少使用的缓存项
  private static LoadingCache<String, Integer> countCache = CacheBuilder.newBuilder()
      .maximumSize(1000).build(new CacheLoader<String, Integer>() {
        @Override
        public Integer load(String key) {
          return createExpensiveGraph(key);
        }
      });

  protected static Integer createExpensiveGraph(String key) {
    return 0;
  }

  /**
   * 消息最大处理次数
   */
  private static final int MAX_RETRY_TIME = 3;




//  @Autowired
//  private RabbitMQMessageSender rabbitMQMessageSender;

  //对消息进行手工确认
  @Override
  public void onMessage(Message message, Channel channel) throws Exception {
    CacheBuilder.newBuilder();
    boolean isAck = false;
    try {
      //logger.info("====消息id为：{}", message.getMessageProperties().getMessageId());
      // 处理消息，避免无限发送
      boolean isContinue = preProcess(message, channel);
      if (!isContinue) {
        isAck = true;
        return;
      }
      byte[] body = message.getBody();
      String messageStr = new String(body, CHARSET_GBK);

      MqMessage mqMessage = JSON.parseObject(messageStr, MqMessage.class);


      if(JSON.toJSONString(mqMessage).contains("PDF_GEN_RESULT")) {
        return;
      }

      if(JSON.toJSONString(mqMessage).contains("<Kp>")){
        String result = returnInvoice.callBack(returnInvoice.pInvoice(mqMessage));
        logger.info(result);
          stringToFile.pSave(mqMessage, pInPut, pOutPut);

      }else{
        String result= returnInvoice.callBack(returnInvoice.eInvoice(mqMessage));
        logger.info(result);
          stringToFile.eSave(mqMessage, eInPut, eOutPut);

      }
      logger.info(JSON.toJSONString(message));
      logger.info(JSON.toJSONString(mqMessage));


    } catch (Exception ex) {
      isAck = true;
      logger.error(ex.getMessage(), ex);
      doNack(message, channel);
      throw new BusinessRuntimeException(ex.getMessage(), ex);
    } finally {
      if (!isAck) {
        this.doAck(message, channel);
      }
    }
  }



  public void doAck(Message message, Channel channel) {
    try {
      channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    } catch (IOException e) {
      logger.error("消息Ack错误", e.getMessage());
      throw new BusinessRuntimeException(e.getMessage());
    }
  }

  /**
   * @param message
   * @param channel
   */
  private void doNack(Message message, Channel channel) {
    try {
      channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
    } catch (IOException e) {
      logger.error("消息Nack错误", e.getMessage());
      throw new BusinessRuntimeException(e.getMessage());
    }
  }

  /**
   * @param message
   * @param channel
   * @return true if it is continue running, else stop
   */
  private boolean preProcess(Message message, Channel channel) throws ExecutionException {
    final String key = getKey(message);
    Integer count = countCache.get(key);
    if (count < MAX_RETRY_TIME) {
      countCache.put(key, ++count);
    } else {
      countCache.invalidate(key);
      doAck(message, channel);
      return false;
    }
    return true;
  }

  private String getKey(Message message) {
    return MESSAGE_PREFIX + message.getMessageProperties().getMessageId();
  }
}
