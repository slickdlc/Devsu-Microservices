package com.devsu.customer.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.devsu.customer")
public class CustomerApplication {

  public static void main(final String[] args) {
    SpringApplication.run(CustomerApplication.class, args);
  }
}
