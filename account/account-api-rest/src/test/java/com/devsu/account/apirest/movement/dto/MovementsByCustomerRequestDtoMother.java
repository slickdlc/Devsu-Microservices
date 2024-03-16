package com.devsu.account.apirest.movement.dto;

import java.time.LocalDate;

import com.devsu.account.apirest.movement.dto.MovementsByCustomerRequestDto;
import com.devsu.entity.AccountMother;
import com.devsu.entity.CustomerMother;
import com.devsu.filter.MovementsByCustomerFilterMother;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MovementsByCustomerRequestDtoMother {

  public MovementsByCustomerRequestDto.MovementsByCustomerRequestDtoBuilder builder() {
    return MovementsByCustomerRequestDto.builder()
        .startDate(MovementsByCustomerFilterMother.START_DATE)
        .endDate(MovementsByCustomerFilterMother.END_DATE)
        .customerId(CustomerMother.CUSTOMER_ID);
  }

  public MovementsByCustomerRequestDto complete() {
    return builder().build();
  }

  public MovementsByCustomerRequestDto withStartDate(final LocalDate startDate) {
    return builder()
        .startDate(startDate)
        .build();
  }

  public MovementsByCustomerRequestDto withEndDate(final LocalDate endDate) {
    return builder()
        .endDate(endDate)
        .build();
  }

  public MovementsByCustomerRequestDto withCustomerId(final Integer customerId) {
    return builder()
        .customerId(customerId)
        .build();
  }

  public MovementsByCustomerRequestDto withCustomerIdentification(final String customerIdentification) {
    return builder()
        .customerIdentification(customerIdentification)
        .build();
  }

}
