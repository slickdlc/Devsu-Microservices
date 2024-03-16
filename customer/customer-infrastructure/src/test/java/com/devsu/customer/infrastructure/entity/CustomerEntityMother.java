package com.devsu.customer.infrastructure.entity;

import com.devsu.CustomerMother;
import com.devsu.customer.infrastructure.entity.CustomerEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerEntityMother {

  public CustomerEntity.CustomerEntityBuilder builder() {
    return CustomerEntity.builder()
        .name(CustomerMother.NAME)
        .gender(CustomerMother.GENDER)
        .age(CustomerMother.AGE)
        .identification(CustomerMother.IDENTIFICATION)
        .address(CustomerMother.ADDRESS)
        .phone(CustomerMother.PHONE)
        .password(CustomerMother.PASSWORD)
        .active(CustomerMother.ACTIVE);
  }

  public CustomerEntity complete() {
    return builder().build();
  }
}
