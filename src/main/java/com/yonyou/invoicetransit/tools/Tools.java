package com.yonyou.invoicetransit.tools;

import com.alibaba.fastjson.JSON;
import com.yonyou.invoicetransit.entity.MqMessage;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据提取和装填工具类
 */
public class Tools {

  public static String getFpqqlsh(MqMessage mqMessage){

    String string1=StringUtils.substringBefore(JSON.toJSONString(mqMessage),"</FPQQLSH>");

    String string=StringUtils.substringAfter(string1,"<FPQQLSH>");

    return string;
  }
}
