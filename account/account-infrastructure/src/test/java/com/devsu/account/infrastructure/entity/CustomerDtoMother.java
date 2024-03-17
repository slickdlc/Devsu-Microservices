package com.devsu.account.infrastructure.entity;

import com.devsu.account.infrastructure.dto.CustomerDto;
import com.devsu.entity.CustomerMother;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerDtoMother {

  public static final boolean ACTIVE = true;

  public static CustomerDto.CustomerDtoBuilder builder() {
    return CustomerDto.builder()
        .clienteId(CustomerMother.CUSTOMER_ID)
        .nombre(CustomerMother.CUSTOMER_NAME)
        .identificacion(CustomerMother.CUSTOMER_IDENTIFICATION)
        .activo(CustomerDtoMother.ACTIVE);
  }

  public static CustomerDto complete() {
    return builder().build();
  }

  public static CustomerDto withActive(boolean active) {
    return builder().activo(active).build();
  }
}
