package com.yonyou.invoicetransit.enums;

/**
 * Created by yangchuanhua on 2017/11/23.
 */
public enum ZSFSEnum {
  /**
   * 普通征税
   */
  NORMAL("0", "普通征税"),
  /**
   * 差额征税
   */
  DIFF("2", "差额征税");

  private String value;

  private String desc;

  private ZSFSEnum(String value, String desc) {
    this.value = value;
    this.desc = desc;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }
}
