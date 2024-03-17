package com.devsu.account.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.devsu.account")
public class AccountApplication {

  public static void main(final String[] args) {
    SpringApplication.run(AccountApplication.class, args);
  }
}
