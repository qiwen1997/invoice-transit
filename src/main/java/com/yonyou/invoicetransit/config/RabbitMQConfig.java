package com.yonyou.invoicetransit.config;

import com.yonyou.invoicetransit.converter.FastJsonMessageConverter;
import com.yonyou.invoicetransit.mq.InvoiceMQListener;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Value("${spring.rabbitmq.host}")
  private String host;

  @Value("${spring.rabbitmq.username}")
  private String userName;

  @Value("${spring.rabbitmq.password}")
  private String passWord;

  @Value("${spring.rabbitmq.virtual-host}")
  private String vHost;

  @Value("${invoice.mq.queues}")
  private String queues;

  private static final String EXCHANGE_NAME = "iuap-direct-exchange";

  private static final String ROUTINGKEY_FLAG = "_KEY";

  @Bean
  public ConnectionFactory connectionFactory(){
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
    connectionFactory.setAddresses(host);
    connectionFactory.setUsername(userName);
    connectionFactory.setPassword(passWord);
    connectionFactory.setVirtualHost(vHost);
    return connectionFactory;
  }

  @Bean
  RabbitAdmin rabbitAdmin(@Autowired ConnectionFactory connectionFactory) {
    RabbitAdmin admin = new RabbitAdmin(connectionFactory);
    admin.setAutoStartup(true);
    return admin;
  }

  @Bean
  RabbitTemplate rabbitTemplate(@Autowired ConnectionFactory connectionFactory) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    FastJsonMessageConverter fastJsonMessageConverter = new FastJsonMessageConverter();
    fastJsonMessageConverter.setDefaultCharset("GBK");
    rabbitTemplate.setMessageConverter(fastJsonMessageConverter);
    return rabbitTemplate;
  }

  @Bean
  SimpleMessageListenerContainer container(@Autowired ConnectionFactory connectionFactory,
      @Autowired InvoiceMQListener messageListener) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    if (queues != null) {
      container.setQueueNames(queues);
    }
    //AcknowledgeMode.MANUAL模式需要人为地获取到channel之后调用方法向server发送ack（或消费失败时的nack）信息。
    container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
    container.setExposeListenerChannel(true);
    container.setMaxConcurrentConsumers(1);
    container.setConcurrentConsumers(1);
    container.setMessageListener(messageListener);
    return container;
  }
}
