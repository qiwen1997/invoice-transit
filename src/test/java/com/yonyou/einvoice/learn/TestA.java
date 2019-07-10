package com.yonyou.einvoice.learn;

import org.junit.Test;
import org.junit.experimental.categories.Category;

class Feature1 {}
class Feature2 {}

public class TestA {
  @Test
  @Category(Feature1.class)
  public void testAdd() {
    System.out.println("A.testAdd");
  }

  @Test
  @Category(Feature2.class)
  public void testAdd2() {
    System.out.println("A.testAdd2");
  }

  @Test
  @Category({Feature1.class, Feature2.class})
  public void testAdd3() {
    System.out.println("A.testAdd3");
  }
}