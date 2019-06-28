/**
 * 
 */
package com.yonyou.invoicetransit.entity;

import java.util.UUID;

/**
 * mq通讯上下文信息
 * 
 *
 */
public class MqContext {

  /**
   * 消息id,后续用来控制无限重传
   */
  private String id;

  /**
   * 公司id
   */
  private String corpid;

  /**
   * 纳税人识别号
   */
  private String nsrsbh;

  /**
   * 消息类型
   */
  private String type;

  /**
   * 税控设备编号
   */
  private String equipmentCode;

  /**
   * 
   */
  public MqContext() {
    this.id = UUID.randomUUID().toString();
  }

  /**
   * @return the corpid
   */
  public String getCorpid() {
    return corpid;
  }

  /**
   * @param corpid the corpid to set
   */
  public void setCorpid(String corpid) {
    this.corpid = corpid;
  }

  /**
   * @return the nsrsbh
   */
  public String getNsrsbh() {
    return nsrsbh;
  }

  /**
   * @param nsrsbh the nsrsbh to set
   */
  public void setNsrsbh(String nsrsbh) {
    this.nsrsbh = nsrsbh;
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
   * @return the equipmentId
   */
  public String getEquipmentCode() {
    return equipmentCode;
  }

  /**
   * @param equipmentCode the equipmentId to set
   */
  public void setEquipmentCode(String equipmentCode) {
    this.equipmentCode = equipmentCode;
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "MqContext{" +
        "id='" + id + '\'' +
        ", corpid='" + corpid + '\'' +
        ", nsrsbh='" + nsrsbh + '\'' +
        ", type='" + type + '\'' +
        ", equipmentCode='" + equipmentCode + '\'' +
        '}';
  }
}
