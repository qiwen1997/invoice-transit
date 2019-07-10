package com.yonyou.einvoice.learn;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@IncludeCategory(Feature1.class)
@ExcludeCategory(Feature2.class)
@Suite.SuiteClasses({ TestA.class, /*Any test class you want to run*/})
public class TestCategory {
  // Do nothing
}