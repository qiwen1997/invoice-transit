package com.yonyou.invoicetransit.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;

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
}
