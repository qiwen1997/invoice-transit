package com.yonyou.einvoice.service;

import com.yonyou.einvoice.entity.MqMessage;
import com.yonyou.einvoice.entity.MqResult;

public interface ReturnInvoice {

  /**
   * 电子发票生成context
   * @param fpqqlsh
   * @return
   */
  public String electronToXML(String fpqqlsh);

  /**
   * 纸质发票生成context
   * @param fpqqlsh
   * @return
   */
  public String paperToXML(String fpqqlsh);

  /**
   * 生成电子发票需要返回的实体字符串
   * @param mqMessage
   * @return
   */
  public String eInvoice(MqMessage mqMessage) throws Exception;

  /**
   * 生成纸质发票需要返回的实体字符串
   * @param mqMessage
   * @return
   */
  public String pInvoice(MqMessage mqMessage) throws Exception;

  /**
   * 把data部分转换成Base64
   * @param mqResult
   * @return
   */
  public String convertToBase64MqMessage(MqResult mqResult) throws Exception;

  /**
   * 调用助手消息回传接口
   * @param json
   * @return
   */
  public String callBack(String json) throws Exception;

}