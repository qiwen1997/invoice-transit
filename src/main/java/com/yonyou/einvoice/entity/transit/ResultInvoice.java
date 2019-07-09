package com.yonyou.einvoice.entity.transit;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 最终返回的开具发票的结果实体
 */
public class ResultInvoice {

  @JSONField(name="business")
  private Business business;

  public ResultInvoice() {
  }

  public Business getBusiness() {
    return business;
  }

  public void setBusiness(Business business) {
    this.business = business;
  }
}
