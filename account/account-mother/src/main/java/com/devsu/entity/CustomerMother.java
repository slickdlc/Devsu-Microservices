package com.devsu.entity;

import com.devsu.domain.entity.Customer;
import com.devsu.domain.entity.Customer.CustomerBuilder;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerMother {

  public static final Integer CUSTOMER_ID = 1;

  public static final String CUSTOMER_IDENTIFICATION = "12345678";

  public static final String CUSTOMER_NAME = "Juan Perez";

  public static CustomerBuilder builder() {
    return Customer.builder();
  }

  public static Customer complete() {
    return Customer.builder()
        .customerId(CUSTOMER_ID)
        .customerName(CUSTOMER_NAME)
        .identification(CUSTOMER_IDENTIFICATION)
        .build();
  }
}