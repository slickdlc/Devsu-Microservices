package com.devsu.account.boot.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

  @Value("${rest-service.customer.base-url}")
  private String customerBaseUrl;

  @Bean
  public RestTemplate customerRestTemplate(final RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder
        .rootUri(this.customerBaseUrl)
        .build();
  }
}
