package com.yonyou.einvoice.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.UnsupportedEncodingException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

public class FastJsonMessageConverter extends AbstractMessageConverter {


  private static final Logger logger = LoggerFactory.getLogger(FastJsonMessageConverter.class);

  private static final String CLASS_TYPE = "CLASS_TYPE";

 // public static final String DEFAULT_CHARSET = "UTF-8";

  private volatile String defaultCharset = "UTF-8";

  @Override
  protected Message createMessage(Object o, MessageProperties messageProperties) {
    String jsonString = JSON.toJSONString(o, SerializerFeature.WriteDateUseDateFormat);
    byte[] bytes = null;
    try {
      bytes = jsonString.getBytes(defaultCharset);
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage(), e);
      throw new MessageConversionException("Failed to convert Message content", e);
    }
    // 在header中设置传输对象类型，用于反序列化
    messageProperties.setHeader(CLASS_TYPE, o.getClass().getName());
    messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
    messageProperties.setContentEncoding(defaultCharset);
    if (bytes != null) {
      messageProperties.setContentLength(bytes.length);
    }
    return new Message(bytes, messageProperties);
  }

  @Override
  public Object fromMessage(Message message) throws MessageConversionException {
    try {
      Class name = JSONObject.class;
      String className = (String) message.getMessageProperties().getHeaders().get(CLASS_TYPE);
      if (StringUtils.isNotBlank(className)) {
        name = Class.forName(className);
      }
      String json = new String(message.getBody(), defaultCharset);
      return JSON.parseObject(json, name);
    } catch (UnsupportedEncodingException e) {
      logger.error(e.getMessage(), e);
    } catch (ClassNotFoundException e) {
      logger.error(e.getMessage(), e);
    }
    return null;
  }

  public void setDefaultCharset(String defaultCharset) {
    this.defaultCharset = defaultCharset;
  }
}
