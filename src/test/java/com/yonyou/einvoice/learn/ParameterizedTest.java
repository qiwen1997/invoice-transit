package com.yonyou.einvoice.learn;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParameterizedTest {

  private int expected;
  private int first;
  private int second;

  public ParameterizedTest(int expected, int firstNumber, int secondNumber) {
    this.expected = expected;
    this.first = firstNumber;
    this.second = secondNumber;
  }

  /**
   * Note: @Parameters annotated method must be public static,
   * otherwise an Exception will thrown.
   */
  @Parameters
  public static List<Integer[]> parameters() {
    return Arrays.asList(new Integer[][]{{4, 1, 2}, {5, 2, 3}, {7, 3, 4}, {9, 4, 5}});
  }

  @Test
  public void testAdd() {
    String format = "Using parameters: expect=%d, first=%d, second=%d";
    System.out.println(String.format(format, expected, first, second));

    Feature feature = new Feature();
    assertEquals("断言",expected, feature.add(first, second));
    //Assert.
  }

  @Test
  public void testPrint() {
    String format = "Print ----------: expect=%d, first=%d, second=%d";
    System.out.println(String.format(format, expected, first, second));
  }
}

class Feature {
  public int add(int i1, int i2) {
    return i1 + i2;
  }
}
