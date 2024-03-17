package com.devsu.account.apirest.account.dto;

import java.math.BigDecimal;

import com.devsu.entity.AccountMother;
import com.devsu.account.apirest.account.dto.AccountRequestDto.AccountRequestDtoBuilder;
import com.devsu.entity.CustomerMother;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountRequestDtoMother {

  public AccountRequestDtoBuilder builder() {
    return AccountRequestDto.builder()
        .clienteId(CustomerMother.CUSTOMER_ID)
        .numeroDeCuenta(AccountMother.ACCOUNT_NUMBER)
        .tipoDeCuenta(AccountMother.ACCOUNT_TYPE)
        .saldoInicial(AccountMother.INITIAL_BALANCE)
        .activo(AccountMother.ACTIVE);
  }

  public AccountRequestDto complete() {
    return builder().build();
  }

  public AccountRequestDto customerId(final Integer customerId) {
    return builder()
        .clienteId(customerId)
        .build();
  }

  public static AccountRequestDto withInitialBalance(final BigDecimal initialBalance) {
    return builder()
        .saldoInicial(initialBalance)
        .build();
  }

  public static AccountRequestDto accountNumber(final String accountNumber) {
    return builder()
        .numeroDeCuenta(accountNumber)
        .build();
  }

  public static AccountRequestDto withAccountType(final String accountType) {
    return builder()
        .tipoDeCuenta(accountType)
        .build();
  }

  public static AccountRequestDto withActive(Boolean active) {
    return builder()
        .activo(active)
        .build();
  }

}
