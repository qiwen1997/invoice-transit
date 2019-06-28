package com.yonyou.invoicetransit.msg;

/**
 * 平台失败消息（回传客户端使用）
 * 
 * @author wangweir
 *
 */
public class EinvoicePdfGenResult {

  private String fpqqlsh;

  private String errorMsg;

  private String status;

  public static final String FAILED = "开票失败";

  public static final String SUCCESS = "开票成功";

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
   * @return the errorMsg
   */
  public String getErrorMsg() {
    return errorMsg;
  }

  /**
   * @param errorMsg the errorMsg to set
   */
  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  /**
   * @return the status
   */
  public String getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }

}
