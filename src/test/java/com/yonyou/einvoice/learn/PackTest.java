package com.yonyou.einvoice.learn;

import com.yonyou.einvoice.MQListenerTest;
import com.yonyou.einvoice.TransitTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(Suite.class)
@SuiteClasses({TransitTest.class, MQListenerTest.class})
public class PackTest {

  @Test
  public void testPrint() {
    System.out.println("Hello");
  }
}
