package com.yonyou.einvoice.service;

import com.alibaba.fastjson.JSON;
import com.yonyou.einvoice.entity.MqMessage;
import com.yonyou.einvoice.util.Tools;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *文件保存相关业务功能实现
 * created by qiwen on 2019/7/19
 */
@Slf4j
@Service
public class StringToFileImpl implements StringToFile{

  @Autowired
  private ReturnInvoice returnInvoice;

  @Autowired
  private StringToFile stringToFile;

  /**
   * 将字符串写入指定文件(当指定的父路径中文件夹不存在时，会最大限度去创建，以保证保存成功！)
   *
   * @param res            原字符串
   * @param filePath 文件路径
   * @return 成功标记
   */
  @Override
  public  boolean stringFile(String res, String filePath) throws Exception {
    boolean flag = true;
    File distFile = new File(filePath);
    if (!distFile.getParentFile().exists()) {
      boolean b=distFile.getParentFile().mkdirs();
    }
    Path path=distFile.toPath();

    byte[] bytes=res.getBytes("GBK");
    Files.write(path,bytes);
    return flag;
  }
  //保存电子发票
  @Override
  public void eSave(MqMessage mqMessage,String inPath,String outPath){
    String xml= Tools.getXML(JSON.toJSONString(mqMessage));

    String name="电子发票_"+Tools.getFpqqlshJSON(JSON.toJSONString(mqMessage))+".xml";
    try {
      stringToFile.stringFile(xml,inPath+name);
    } catch (Exception e) {
      log.error("字符串转文件异常",e.getMessage());
    }
    String resultXml= returnInvoice.electronToXML(Tools.getFpqqlshJSON(JSON.toJSONString(mqMessage)));

    String resultName="电子发票_"+Tools.getFpqqlshJSON(JSON.toJSONString(mqMessage))+"_result.xml";
    try {
      stringToFile.stringFile(resultXml,outPath+resultName);
    } catch (Exception e) {
      log.error("字符串转文件异常",e.getMessage());
    }
  }
  //保存纸质发票
  @Override
  public  void pSave(MqMessage mqMessage,String inPath,String outPath){
    String xml=Tools.getXML(JSON.toJSONString(mqMessage));

    String name="纸质发票_"+Tools.getFpqqlshJSON(JSON.toJSONString(mqMessage))+".xml";
    try {
      stringToFile.stringFile(xml,inPath+name);
    } catch (Exception e) {
      log.error("字符串转文件异常",e.getMessage());
    }
    String resultXml= returnInvoice.paperToXML(Tools.getFpqqlshJSON(JSON.toJSONString(mqMessage)));

    String resultName="纸质发票_"+Tools.getFpqqlshJSON(JSON.toJSONString(mqMessage))+"_模拟开票结果.xml";
    try {
      stringToFile.stringFile(resultXml,outPath+resultName);
    } catch (Exception e) {
      log.error("字符串转文件异常",e.getMessage());
    }
  }


}
