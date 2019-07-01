package com.yonyou.invoicetransit.entity.transit;

import com.alibaba.fastjson.annotation.JSONField;
import com.yonyou.invoicetransit.tools.RandomCharData;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 返回结果中装载数据的实体
 */
public class ResponseCommonFpkj {

  //常量定义发票密文
  private static final String FPMW="<-<>48938<4+<14>735+<2554*8-1-<+15<*026+848686/2/3//0>+*>>>356*<757/47>90+<25<<3575**934<+15<*026+848686--57";

  //发票流水号
  @JSONField(name="FPQQLSH",ordinal =1)
  private String fpqqlsh;

  //机器编码
  @JSONField(name="JQBH",ordinal =2)
  private String jqbh;

  //发票代码
  @JSONField(name="FP_DM",ordinal =3)
  private String fpdm;

  //发票号码
  @JSONField(name="FP_HM",ordinal =4)
  private String fphm;

  //开票日期
  @JSONField(name="KPRQ",ordinal =5)
  private String kprq;

  //发票密文
  @JSONField(name="FP_MW",ordinal =6)
  private String fpmw;

  //校验码
  @JSONField(name="JYM",ordinal =7)
  private String jym;

  //二维码
  @JSONField(name="EWM",ordinal =8)
  private String ewm;

  @JSONField(name="BZ",ordinal =9)
  private String bz;

  //返回代码
  @JSONField(name="RETURNCODE",ordinal =10)
  private String returnCode;

  //返回信息
  @JSONField(name="RETURNMSG",ordinal =11)
  private String returnMsg;

  public ResponseCommonFpkj(String fpqqlsh) {
    this.fpqqlsh = fpqqlsh;
    this.jqbh=RandomCharData.createData(12);
    this.fpdm=RandomCharData.createFPDM();
    this.fphm=RandomCharData.createData(8);
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    this.kprq=df.format(new Date());
    this.fpmw=FPMW;
    this.jym=RandomCharData.createData(20);
    this.ewm="";
    this.bz="";
    this.returnCode="0000";
    this.returnMsg="发票开具成功";
  }

  public ResponseCommonFpkj() {
    this.jqbh=RandomCharData.createData(12);
    this.fpdm=RandomCharData.createFPDM();
    this.fphm=RandomCharData.createData(8);
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    this.kprq=df.format(new Date());
    this.fpmw=FPMW;
    this.jym=RandomCharData.createData(20);
    this.ewm="";
    this.bz="";
    this.returnCode="0000";
    this.returnMsg="发票开具成功";
  }

  public static String getFPMW() {
    return FPMW;
  }

  public String getFpqqlsh() {
    return fpqqlsh;
  }

  public void setFpqqlsh(String fpqqlsh) {
    this.fpqqlsh = fpqqlsh;
  }

  public String getJqbh() {
    return jqbh;
  }

  public void setJqbh(String jqbh) {
    this.jqbh = jqbh;
  }

  public String getFpdm() {
    return fpdm;
  }

  public void setFpdm(String fpdm) {
    this.fpdm = fpdm;
  }

  public String getFphm() {
    return fphm;
  }

  public void setFphm(String fphm) {
    this.fphm = fphm;
  }

  public String getKprq() {
    return kprq;
  }

  public void setKprq(String kprq) {
    this.kprq = kprq;
  }

  public String getFpmw() {
    return fpmw;
  }

  public void setFpmw(String fpmw) {
    this.fpmw = fpmw;
  }

  public String getJym() {
    return jym;
  }

  public void setJym(String jym) {
    this.jym = jym;
  }

  public String getEwm() {
    return ewm;
  }

  public void setEwm(String ewm) {
    this.ewm = ewm;
  }

  public String getBz() {
    return bz;
  }

  public void setBz(String bz) {
    this.bz = bz;
  }

  public String getReturnCode() {
    return returnCode;
  }

  public void setReturnCode(String returnCode) {
    this.returnCode = returnCode;
  }

  public String getReturnMsg() {
    return returnMsg;
  }

  public void setReturnMsg(String returnMsg) {
    this.returnMsg = returnMsg;
  }
}
