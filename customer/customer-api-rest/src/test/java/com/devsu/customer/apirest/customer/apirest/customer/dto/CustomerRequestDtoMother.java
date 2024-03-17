package com.devsu.customer.apirest.customer.apirest.customer.dto;

import com.devsu.CustomerMother;
import com.devsu.customer.apirest.customer.dto.CustomerRequestDto;
import com.devsu.customer.apirest.customer.dto.CustomerRequestDto.CustomerRequestDtoBuilder;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerRequestDtoMother {

  public CustomerRequestDtoBuilder builder() {
    return CustomerRequestDto.builder()
        .nombre(CustomerMother.NAME)
        .genero(CustomerMother.GENDER)
        .edad(CustomerMother.AGE)
        .identificacion(CustomerMother.IDENTIFICATION)
        .direccion(CustomerMother.ADDRESS)
        .telefono(CustomerMother.PHONE)
        .contrasena(CustomerMother.PASSWORD)
        .activo(CustomerMother.ACTIVE);
  }

  public CustomerRequestDto complete() {
    return builder().build();
  }

  public CustomerRequestDto withName(final String name) {
    return builder()
        .nombre(name)
        .build();
  }

  public static CustomerRequestDto withAge(final Integer age) {
    return builder()
        .edad(age)
        .build();
  }

  public static CustomerRequestDto withIdentification(final String identification) {
    return builder()
        .identificacion(identification)
        .build();
  }

  public static CustomerRequestDto withAddress(final String address) {
    return builder()
        .direccion(address)
        .build();
  }

  public static CustomerRequestDto withPhone(final String phone) {
    return builder()
        .telefono(phone)
        .build();
  }

  public static CustomerRequestDto withPassword(final String password) {
    return builder()
        .contrasena(password)
        .build();
  }

  public static CustomerRequestDto withActive(Boolean active) {
    return builder()
        .activo(active)
        .build();
  }

}
