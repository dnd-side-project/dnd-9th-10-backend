package com.dnd.bbok.infra.feign.config;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.dnd.bbok.infra")
public class KakaoFeignConfig {

  @Bean
  Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }

  @Bean
  public RequestInterceptor requestInterceptor() {
    return template -> template.header("Content-Type", "application/x-www-form-urlencoded");
  }

//  @Bean
//  public ErrorDecoder errorDecoder() {
//    return new FeignClientExceptionErrorDecoder();
//  }

}
