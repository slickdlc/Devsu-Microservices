package com.devsu.account.apirest.account.dto;

import java.math.BigDecimal;

import com.devsu.entity.AccountMother;
import com.devsu.account.apirest.account.dto.AccountRequestDto;
import com.devsu.account.apirest.account.dto.AccountRequestDto.AccountRequestDtoBuilder;
import com.devsu.entity.CustomerMother;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountRequestDtoMother {

  public AccountRequestDtoBuilder builder() {
    return AccountRequestDto.builder()
        .customerId(CustomerMother.CUSTOMER_ID)
        .accountNumber(AccountMother.ACCOUNT_NUMBER)
        .accountType(AccountMother.ACCOUNT_TYPE)
        .initialBalance(AccountMother.INITIAL_BALANCE)
        .active(AccountMother.ACTIVE);
  }

  public AccountRequestDto complete() {
    return builder().build();
  }

  public AccountRequestDto customerId(final Integer customerId) {
    return builder()
        .customerId(customerId)
        .build();
  }

  public static AccountRequestDto withInitialBalance(final BigDecimal initialBalance) {
    return builder()
        .initialBalance(initialBalance)
        .build();
  }

  public static AccountRequestDto accountNumber(final String accountNumber) {
    return builder()
        .accountNumber(accountNumber)
        .build();
  }

  public static AccountRequestDto withAccountType(final String accountType) {
    return builder()
        .accountType(accountType)
        .build();
  }

  public static AccountRequestDto withActive(Boolean active) {
    return builder()
        .active(active)
        .build();
  }

}
