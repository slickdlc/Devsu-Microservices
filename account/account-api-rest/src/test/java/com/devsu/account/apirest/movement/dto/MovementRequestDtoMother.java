package com.devsu.account.apirest.movement.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.devsu.entity.AccountMother;
import com.devsu.entity.MovementMother;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MovementRequestDtoMother {

  public MovementRequestDto.MovementRequestDtoBuilder builder() {
    return MovementRequestDto.builder()
        .cuentaId(AccountMother.ACCOUNT_ID)
        .fechaMovimiento(MovementMother.TIMESTAMP)
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

  public static MovementRequestDto withTimestamp(Instant timestamp) {
    return builder()
        .fechaMovimiento(timestamp)
        .build();
  }

}
