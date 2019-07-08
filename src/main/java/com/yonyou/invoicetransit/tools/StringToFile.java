package com.yonyou.invoicetransit.tools;

import com.alibaba.fastjson.JSON;
import com.yonyou.invoicetransit.entity.MqMessage;
import com.yonyou.invoicetransit.simulation.ReturnInvoice;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.text.SimpleDateFormat;

/**
 * 字符串转换成文件
 */
public class StringToFile {

  /**
   * 将字符串写入指定文件(当指定的父路径中文件夹不存在时，会最大限度去创建，以保证保存成功！)
   *
   * @param res            原字符串
   * @param filePath 文件路径
   * @return 成功标记
   */
  public static boolean stringFile(String res, String filePath) throws Exception {
    boolean flag = true;
    BufferedReader reader=null;
    try {
      File distFile = new File(filePath);
      if (!distFile.getParentFile().exists()) {
        distFile.getParentFile().mkdirs();
      }
      //InputStreamReader inStream = new InputStreamReader(new StringReader(str), "GBK");

      OutputStreamWriter writerStream = new OutputStreamWriter(new FileOutputStream(filePath),"GBK");

      reader = new BufferedReader(new StringReader(res));
      BufferedWriter writer = new BufferedWriter(writerStream);
      String lineWriter = null;
      while ((lineWriter = reader.readLine()) != null) {
        writer.write(lineWriter);
        writer.newLine();
        writer.flush();
      }
      reader.close();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
      flag = false;
      return flag;
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return flag;
  }
  //保存电子发票
  public static void eSave(MqMessage mqMessage,String inPath,String outPath){
    String xml= Tools.getXML(JSON.toJSONString(mqMessage));

    String name="电子发票_"+Tools.getFpqqlshJSON(JSON.toJSONString(mqMessage))+".xml";
    try {
      StringToFile.stringFile(xml,inPath+name);
    } catch (Exception e) {
      e.printStackTrace();
    }
    String resultXml= ReturnInvoice.toXML(Tools.getFpqqlshJSON(JSON.toJSONString(mqMessage)));

    String resultName="电子发票_"+Tools.getFpqqlshJSON(JSON.toJSONString(mqMessage))+"_result.xml";
    try {
      StringToFile.stringFile(resultXml,outPath+resultName);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  //保存纸质发票
  public static void pSave(MqMessage mqMessage,String inPath,String outPath){
    String xml=Tools.getXML(JSON.toJSONString(mqMessage));

    String name="纸质发票_"+Tools.getFpqqlshJSON(JSON.toJSONString(mqMessage))+".xml";
    try {
      StringToFile.stringFile(xml,inPath+name);
    } catch (Exception e) {
      e.printStackTrace();
    }
    String resultXml=ReturnInvoice.paperToXML(Tools.getFpqqlshJSON(JSON.toJSONString(mqMessage)));

    String resultName="纸质发票_"+Tools.getFpqqlshJSON(JSON.toJSONString(mqMessage))+"_模拟开票结果.xml";
    try {
      StringToFile.stringFile(resultXml,outPath+resultName);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
