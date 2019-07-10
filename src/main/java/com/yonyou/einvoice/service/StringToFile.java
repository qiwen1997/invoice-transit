package com.yonyou.einvoice.service;

import com.yonyou.einvoice.entity.MqMessage;

public interface StringToFile {

  /**
   * 将字符串写入指定文件(当指定的父路径中文件夹不存在时，会最大限度去创建，以保证保存成功！)
   *
   * @param res            原字符串
   * @param filePath 文件路径
   * @return 成功标记
   */
   boolean stringFile(String res, String filePath) throws Exception;

  /**
   * 保存电子发票
   * @param mqMessage
   * @param inPath
   * @param outPath
   */
   void eSave(MqMessage mqMessage,String inPath,String outPath);

  /**
   * 保存纸质发票
   * @param mqMessage
   * @param inPath
   * @param outPath
   */
   void pSave(MqMessage mqMessage,String inPath,String outPath);


}
