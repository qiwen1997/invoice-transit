package com.yonyou.einvoice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeplpoyController {

  @RequestMapping("test")
  public String test(){
    return "部署成功";
  }
}
