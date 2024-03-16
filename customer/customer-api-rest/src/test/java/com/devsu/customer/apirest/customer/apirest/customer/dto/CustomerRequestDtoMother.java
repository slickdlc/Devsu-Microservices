package com.devsu.customer.apirest.customer.apirest.customer.dto;

import com.devsu.CustomerMother;
import com.devsu.customer.apirest.customer.dto.CustomerRequestDto;
import com.devsu.customer.apirest.customer.dto.CustomerRequestDto.CustomerRequestDtoBuilder;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerRequestDtoMother {

  public CustomerRequestDtoBuilder builder() {
    return CustomerRequestDto.builder()
        .name(CustomerMother.NAME)
        .gender(CustomerMother.GENDER)
        .age(CustomerMother.AGE)
        .identification(CustomerMother.IDENTIFICATION)
        .address(CustomerMother.ADDRESS)
        .phone(CustomerMother.PHONE)
        .password(CustomerMother.PASSWORD)
        .active(CustomerMother.ACTIVE);
  }

  public CustomerRequestDto complete() {
    return builder().build();
  }

  public CustomerRequestDto withName(final String name) {
    return builder()
        .name(name)
        .build();
  }

  public static CustomerRequestDto withAge(final Integer age) {
    return builder()
        .age(age)
        .build();
  }

  public static CustomerRequestDto withIdentification(String identification) {
    return builder()
        .identification(identification)
        .build();
  }

  public static CustomerRequestDto withAddress(String address) {
    return builder()
        .address(address)
        .build();
  }

  public static CustomerRequestDto withPhone(String phone) {
    return builder()
        .phone(phone)
        .build();
  }

  public static CustomerRequestDto withPassword(String password) {
    return builder()
        .password(password)
        .build();
  }

  public static CustomerRequestDto withActive(Boolean active) {
    return builder()
        .active(active)
        .build();
  }
  
}
