package com.devsu.account.apirest.account.apirest.account.dto;

import java.math.BigDecimal;

import com.devsu.AccountMother;
import com.devsu.account.apirest.account.dto.AccountRequestDto;
import com.devsu.account.apirest.account.dto.AccountRequestDto.AccountRequestDtoBuilder;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountRequestDtoMother {

  public AccountRequestDtoBuilder builder() {
    return AccountRequestDto.builder()
        .customerId(AccountMother.CUSTOMER_ID)
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

  public static AccountRequestDto accountNumber(String accountNumber) {
    return builder()
        .accountNumber(accountNumber)
        .build();
  }

  public static AccountRequestDto withAccountType(String accountType) {
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
