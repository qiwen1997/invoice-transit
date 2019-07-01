package com.yonyou.invoicetransit.tools;

import java.util.Random;
import org.springframework.stereotype.Component;

/**
 * 随机生成数字工具类
 */

public class RandomCharData {

  private static String[] areaCode=new String[]{"0000","3300","1100","2200","4400"};
  //根据指定长度生成字母和数字的随机数
  //0~9的ASCII为48~57
  //A~Z的ASCII为65~90
  //a~z的ASCII为97~122
//  public static String createRandomCharData(int length)
//  {
//    StringBuilder sb=new StringBuilder();
//    Random rand=new Random();//随机用以下三个随机生成器
//    Random randdata=new Random();
//    int data=0;
//    for(int i=0;i<length;i++)
//    {
//      int index=rand.nextInt(3);
//      //目的是随机选择生成数字，大小写字母
//      switch(index)
//      {
//        case 0:
//          data=randdata.nextInt(10);//仅仅会生成0~9
//          sb.append(data);
//          break;
//        case 1:
//          data=randdata.nextInt(26)+65;//保证只会产生65~90之间的整数
//          sb.append((char)data);
//          break;
//        case 2:
//          data=randdata.nextInt(26)+97;//保证只会产生97~122之间的整数
//          sb.append((char)data);
//          break;
//      }
//    }
//    String result=sb.toString();
//    //System.out.println(result);
//    return result;
//  }

  //根据指定长度生成纯数字的随机数
  public static String createData(int length) {
    StringBuilder sb=new StringBuilder();
    Random rand=new Random();
    for(int i=0;i<length;i++)
    {
      sb.append(rand.nextInt(10));
    }
    String data=sb.toString();
    //System.out.println(length+" random data: "+data);
    return data;
  }

  public static String createFPDM(){
    StringBuilder sb=new StringBuilder();
    Random rand=new Random();
    for(int i=1;i<=12;i++){
      if(i==1){
        sb.append(rand.nextInt(3));
        continue;
      }
      if(i==2){
        sb.append(areaCode[rand.nextInt(5)]);
        i=5;
        continue;
      }
      sb.append(rand.nextInt(10));
    }
    String data=sb.toString();
    return data;
  }
}
