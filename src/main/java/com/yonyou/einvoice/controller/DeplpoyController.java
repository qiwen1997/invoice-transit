package com.yonyou.einvoice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *部署测试
 * created by qiwen on 2019/7/19
 */
@RestController
public class DeplpoyController {

  @RequestMapping("test")
  public String test(){
    return "部署成功";
  }
}
