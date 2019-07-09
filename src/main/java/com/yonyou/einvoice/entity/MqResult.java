package com.yonyou.einvoice.entity;

/**
 * 返回消息实体
 */
public class MqResult {

  private String code;

  private String msg;

  private String content;

  private String invoiceType;

  public MqResult() {
  }

  public MqResult(String content, String invoiceType) {
    this.code="0000";
    this.msg="操作成功";
    this.content = content;
    this.invoiceType = invoiceType;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getInvoiceType() {
    return invoiceType;
  }

  public void setInvoiceType(String invoiceType) {
    this.invoiceType = invoiceType;
  }
}
