package com.yonyou.invoicetransit;

import com.yonyou.invoicetransit.exception.BusinessRuntimeException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExceptionTest {

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
