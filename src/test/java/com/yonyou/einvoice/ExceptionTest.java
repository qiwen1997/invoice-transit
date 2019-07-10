package com.yonyou.einvoice;

import com.yonyou.einvoice.exception.BusinessRuntimeException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvoiceTransitApplication.class)
public class ExceptionTest {

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
  @Test
  public void valueTest(){
    System.out.println(eInPut);
    System.out.println(eOutPut);
    System.out.println(pInPut);
    System.out.println(pOutPut);
    System.out.println(day);
    System.out.println(hour);
    System.out.println(minute);
    System.out.println(second);
    System.out.println(mmcond);
  }


  //测试异常
  @Test
  public void exceptionTest1(){
    throw new BusinessRuntimeException("测试抛异常");
  }
  //测试异常
  @Test
  public void exceptionTest2(){
    throw new BusinessRuntimeException("1111","测试抛异常");
  }
  //测试异常
  @Test
  public void exceptionTest3(){
    throw new BusinessRuntimeException("1111","测试抛异常",new Throwable());
  }
  //测试异常
  @Test
  public void exceptionTest4(){
    throw new BusinessRuntimeException("测试抛异常",new Throwable());
  }
}
