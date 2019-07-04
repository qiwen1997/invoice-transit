package com.yonyou.invoicetransit.entity;

/**
 * Created by yangchuanhua on 2017/11/23.
 */
public class MqMessage<T> {

  private MqContext context;

  private T data;

  public MqMessage() {
    this.context=new MqContext();
  }

  public MqContext getContext() {
    return context;
  }

  public void setContext(MqContext context) {
    this.context = context;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "MqMessage{" +
        "context=" + context +
        ", data=" + data +
        '}';
  }
}
