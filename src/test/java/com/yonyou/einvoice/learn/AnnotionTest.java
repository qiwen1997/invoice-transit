package com.yonyou.einvoice.learn;

import com.yonyou.einvoice.InvoiceTransitApplication;
import com.yonyou.einvoice.service.ReturnInvoice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest//(classes = InvoiceTransitApplication.class)
public class AnnotionTest {

  @Autowired
  private ReturnInvoice returnInvoice;

  @Test
  public void t(){
    System.out.println(returnInvoice.paperToXML("12121212"));
  }
}
