package com.yonyou.einvoice.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yonyou.einvoice.entity.MqMessage;
import java.util.Iterator;
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

  public static String getFpqqlshJSON(String json){

    JSONObject obj = (JSONObject) JSONObject.parse(json);
    // 第一种：使用while遍历方式
    String value=new String();
    Iterator<String> iterator = obj.keySet().iterator();
    while(iterator.hasNext()){
      String key = iterator.next();
      if("data".equals(key)) {
        JSONObject js=(JSONObject) obj.get(key);
        Iterator<String> iter = js.keySet().iterator();
        while(iter.hasNext()){
          String k=iter.next();
          if("fpqqlsh".equals(k)){
            value=js.getString(k);
            break;
          }
        }
      }
    }
    return value;
  }

  public static String getXML(String json){

    JSONObject obj = (JSONObject) JSONObject.parse(json);
    // 第一种：使用while遍历方式
    String value=new String();
    Iterator<String> iterator = obj.keySet().iterator();
    while(iterator.hasNext()){
      String key = iterator.next();
      if("data".equals(key)) {
        JSONObject js=(JSONObject) obj.get(key);
        Iterator<String> iter = js.keySet().iterator();
        while(iter.hasNext()){
          String k=iter.next();
          if("data".equals(k)){
            value=js.getString(k);
            break;
          }
        }
      }
    }
    return value;
  }
}
