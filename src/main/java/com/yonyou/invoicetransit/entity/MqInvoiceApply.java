/**
 * 
 */
package com.yonyou.invoicetransit.entity;



import com.yonyou.invoicetransit.enums.ZSFSEnum;


/**
 * @author
 *
 */
public class MqInvoiceApply {

  /**
   * 发票请求流水号
   */
  private String fpqqlsh;

  /**
   * 发票介质
   */
  private String fpjz;

  /**
   * 发票类型
   */
  private String fplx;
  /**
   * 征税方式
   */
  private String zsfs = ZSFSEnum.NORMAL.getValue();

  /**
   * 发票请求xml数据
   */
  private String data;

  /**
   * @return the fpqqlsh
   */
  public String getFpqqlsh() {
    return fpqqlsh;
  }

  /**
   * @param fpqqlsh the fpqqlsh to set
   */
  public void setFpqqlsh(String fpqqlsh) {
    this.fpqqlsh = fpqqlsh;
  }

  /**
   * @return the fpjz
   */
  public String getFpjz() {
    return fpjz;
  }

  /**
   * @param fpjz the fpjz to set
   */
  public void setFpjz(String fpjz) {
    this.fpjz = fpjz;
  }

  /**
   * @return the data
   */
  public String getData() {
    return data;
  }

  /**
   * @param data the data to set
   */
  public void setData(String data) {
    this.data = data;
  }

  /**
   * @return the fplx
   */
  public String getFplx() {
    return fplx;
  }

  /**
   * @param fplx the fplx to set
   */
  public void setFplx(String fplx) {
    this.fplx = fplx;
  }

  public String getZsfs() {
    return zsfs;
  }

  public void setZsfs(String zsfs) {
    this.zsfs = zsfs;
  }

}
