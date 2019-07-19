package com.yonyou.einvoice.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate配置
 * 读取超时60秒
 * 连接超时5秒
 * created by qiwen on 2019/7/19
 */
@Configuration
public class RestTemplateConfig {

  @Bean
  @ConditionalOnMissingBean({RestOperations.class, RestTemplate.class})
  public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
    return new RestTemplate(factory);
  }

  @Bean
  @ConditionalOnMissingBean({ClientHttpRequestFactory.class})
  public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setReadTimeout(60000);
    factory.setConnectTimeout(5000);
    return factory;
  }

}
