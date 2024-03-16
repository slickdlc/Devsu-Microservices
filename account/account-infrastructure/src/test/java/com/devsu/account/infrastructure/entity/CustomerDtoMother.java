package com.devsu.account.infrastructure.entity;

import com.devsu.account.infrastructure.dto.CustomerDto;
import com.devsu.entity.CustomerMother;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerDtoMother {

  public static final boolean ACTIVE = true;

  public static CustomerDto.CustomerDtoBuilder builder() {
    return CustomerDto.builder()
        .customerId(CustomerMother.CUSTOMER_ID)
        .name(CustomerMother.CUSTOMER_NAME)
        .identification(CustomerMother.CUSTOMER_IDENTIFICATION)
        .active(CustomerDtoMother.ACTIVE);
  }

  public static CustomerDto complete() {
    return builder().build();
  }

  public static CustomerDto withActive(boolean active) {
    return builder().active(active).build();
  }
}
