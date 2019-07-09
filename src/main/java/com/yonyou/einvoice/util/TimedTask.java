package com.yonyou.einvoice.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableScheduling
public class TimedTask {

  private static final Logger logger= LoggerFactory.getLogger(TimedTask.class);

  @Value("${invoice.save.einput}")
  private String eInPut;

  @Value("${invoice.save.eoutput}")
  private String eOutPut;

  @Value("${invoice.save.pinput}")
  private String pInPut;

  @Value("${invoice.save.poutput}")
  private String pOutPut;

  @Value("${save.time.day}")
  private Long day;

  @Value("${save.time.hour}")
  private Long hour;

  @Value("${save.time.minute}")
  private Long minute;

  @Value("${save.time.second}")
  private Long second;

  @Value("${save.time.mmcond}")
  private Long mmcond;

  @Scheduled(cron="0 0/1 * * * ? ")
  public void configureTask(){
    File fileEIn=new File(eInPut);
    logger.info("静态定时任务时间："+ LocalDateTime.now()+"----删除输入电子发票---文件地址："+eInPut);
    overTimeDelete(fileEIn);

    File fileEOut=new File(eOutPut);
    logger.info("静态定时任务时间："+ LocalDateTime.now()+"----删除输出电子发票---文件地址："+eOutPut);
    overTimeDelete(fileEOut);

    File filePIn=new File(pInPut);
    logger.info("静态定时任务时间："+ LocalDateTime.now()+"----删除输入纸质发票---文件地址："+pInPut);
    overTimeDelete(filePIn);

    File filePOut=new File(pOutPut);
    logger.info("静态定时任务时间："+ LocalDateTime.now()+"----删除输出纸质发票---文件地址："+pOutPut);
    overTimeDelete(filePOut);
  }
  //保存时间超过规定时间的文件删除
  public void overTimeDelete(File file) {
    //获得文件里面所有的文件及文件夹
    File[] files = file.listFiles();
    if(files==null||files.length==0){
      return;
    }
    //遍历files里面的所有文件及文件夹
    for(File f : files) {

      //获得绝对路径下的文件及文件夹
      File absFile = f.getAbsoluteFile();

      //计算时间
//      long day = 1;
//      long hour = 24;
//      long minute = 60;
//      long second = 60;
//      long mmcond = 1000;


      long currTime = System.currentTimeMillis();   //当前时间


      long lastTime = absFile.lastModified();     //文件被最后一次修改的时间

      //时间差
      long diffen = currTime - lastTime;

      long thDay = day * hour * minute * second * mmcond;

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");   //2017-5-5 23:32:23:234
      String formatTime = "";

      if(diffen >= thDay) {
        absFile.delete();
        formatTime = sdf.format(System.currentTimeMillis());
        logger.info("删除该文件的时间是：" + "\t"+formatTime+"\t" + "\t删除的文件是：" + absFile.getAbsolutePath());
        if(absFile.isDirectory()) {
          overTimeDelete(absFile);
          absFile.delete();
          formatTime = sdf.format(System.currentTimeMillis());
          logger.info("删除该文件的时间是：" + "\t"+formatTime+"\t" + "\t删除的文件是：" + absFile.getAbsolutePath());
        }
      }

    }
  }
}
