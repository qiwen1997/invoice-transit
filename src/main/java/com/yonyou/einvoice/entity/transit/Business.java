package com.yonyou.einvoice.entity.transit;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 返回结果中间实体
 */
public class Business {

  @JSONField(name="RESPONSE_COMMON_FPKJ")
  private ResponseCommonFpkj responseCommonFpkj;

  public Business() {
  }

  public ResponseCommonFpkj getResponseCommonFpkj() {
    return responseCommonFpkj;
  }

  public void setResponseCommonFpkj(
      ResponseCommonFpkj responseCommonFpkj) {
    this.responseCommonFpkj = responseCommonFpkj;
  }
}
