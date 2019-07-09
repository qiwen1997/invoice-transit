package com.yonyou.einvoice.entity.transit;

import com.alibaba.fastjson.annotation.JSONField;
import com.yonyou.einvoice.util.RandomCharData;
import java.text.SimpleDateFormat;
import java.util.Date;

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

  public static Builder create(){
    return new Builder();
  }

  public ResponseCommonFpkj(Builder builder){
    this.fpqqlsh=builder.fpqqlsh;
    this.jqbh=builder.jqbh;
    this.fpdm=builder.fpdm;
    this.fphm=builder.fphm;
    this.kprq=builder.kprq;
    this.fpmw=builder.fpmw;
    this.jym=builder.jym;
    this.ewm=builder.ewm;
    this.bz=builder.bz;
    this.returnCode=builder.returnCode;
    this.returnMsg=builder.returnMsg;
  }

  public static class Builder{

    private String fpqqlsh="";
    private String jqbh=RandomCharData.createData(12);
    private String fpdm=RandomCharData.createFPDM();
    private String fphm=RandomCharData.createData(8);
    private String kprq;
    private String fpmw=FPMW;
    private String jym=RandomCharData.createData(20);
    private String ewm="";
    private String bz="";
    private String returnCode="0000";
    private String returnMsg="发票开具成功";

    public ResponseCommonFpkj build(){
      SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
      this.kprq=df.format(new Date());
      return new ResponseCommonFpkj(this);
    }

    public Builder setFpqqlsh(String fpqqlsh) {
      this.fpqqlsh = fpqqlsh;
      return this;
    }

    public Builder setJqbh(String jqbh) {
      this.jqbh = jqbh;
      return this;
    }

    public Builder setFpdm(String fpdm) {
      this.fpdm = fpdm;
      return this;
    }

    public Builder setFphm(String fphm) {
      this.fphm = fphm;
      return this;
    }

    public Builder setKprq(String kprq) {
      this.kprq = kprq;
      return this;
    }

    public Builder setFpmw(String fpmw) {
      this.fpmw = fpmw;
      return this;
    }

    public Builder setJym(String jym) {
      this.jym = jym;
      return this;
    }

    public Builder setEwm(String ewm) {
      this.ewm = ewm;
      return this;
    }

    public Builder setBz(String bz) {
      this.bz = bz;
      return this;
    }

    public Builder setReturnCode(String returnCode) {
      this.returnCode = returnCode;
      return this;
    }

    public Builder setReturnMsg(String returnMsg) {
      this.returnMsg = returnMsg;
      return this;
    }
  }
}
