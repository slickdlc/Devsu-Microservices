package com.devsu.filter;

import java.time.LocalDate;

import com.devsu.domain.filter.MovementsByCustomerFilter;
import com.devsu.entity.CustomerMother;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MovementsByCustomerFilterMother {

  public static final LocalDate START_DATE = LocalDate.now();

  public static final LocalDate END_DATE = LocalDate.now().plusDays(1);

  public MovementsByCustomerFilter.MovementsByCustomerFilterBuilder builder() {
    return MovementsByCustomerFilter.builder()
        .startDate(START_DATE)
        .endDate(END_DATE)
        .customerId(CustomerMother.CUSTOMER_ID);
  }

  public MovementsByCustomerFilter complete() {
    return builder().build();
  }

  public MovementsByCustomerFilter withStartDate(final LocalDate startDate) {
    return builder()
        .startDate(startDate)
        .build();
  }

  public MovementsByCustomerFilter withEndDate(final LocalDate endDate) {
    return builder()
        .endDate(endDate)
        .build();
  }

  public MovementsByCustomerFilter withCustomerId(final Integer customerId) {
    return builder()
        .customerId(customerId)
        .build();
  }

  public MovementsByCustomerFilter withCustomerIdentification(final String customerIdentification) {
    return builder()
        .customerIdentification(customerIdentification)
        .build();
  }
}
