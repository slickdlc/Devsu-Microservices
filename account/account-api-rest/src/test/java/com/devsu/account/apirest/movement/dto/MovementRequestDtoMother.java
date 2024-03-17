package com.devsu.account.apirest.movement.dto;

import java.math.BigDecimal;

import com.devsu.entity.AccountMother;
import com.devsu.entity.MovementMother;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MovementRequestDtoMother {

  public MovementRequestDto.MovementRequestDtoBuilder builder() {
    return MovementRequestDto.builder()
        .cuentaId(AccountMother.ACCOUNT_ID)
        .fechaHoraMovimiento("20/03/2024 12:13:15")
        .saldo(MovementMother.INITIAL_BALANCE);
  }

  public MovementRequestDto complete() {
    return builder().build();
  }

  public MovementRequestDto withAccountId(final Integer accountId) {
    return builder()
        .cuentaId(accountId)
        .build();
  }

  public static MovementRequestDto withValue(final BigDecimal value) {
    return builder()
        .saldo(value)
        .build();
  }

  public static MovementRequestDto withAccountNumber(final String accountNumber) {
    return builder()
        .numeroDeCuenta(accountNumber)
        .build();
  }


}
