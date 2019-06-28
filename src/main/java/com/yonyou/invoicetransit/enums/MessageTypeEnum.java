package com.yonyou.invoicetransit.enums;

/**
 * Created by yangchuanhua on 2017/11/23.
 */
public enum MessageTypeEnum {
  /**
   * 平台——>客户端
   */
  INVOICE_APPLY("InvoiceApply", "开票请求"),
  /**
   * 客户端——>平台
   */
  TAX_EQUIPMENT_RESULT("TaxEquipmentResult", "税控设备返回结果"),
  /**
   * 平台——>客户端
   */
  PLATFORM_PDF_RESULT("PDF_GEN_RESULT", "平台版式文件生成结果");



  /**
   * 消息类型
   */
  private String type;
  /**
   * 消息类型名称
   */
  private String name;

  private MessageTypeEnum(String type, String name) {
    this.type = type;
    this.name = name;
  }

  /**
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }
}
