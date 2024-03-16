package com.devsu.entity;

import java.math.BigDecimal;
import java.time.Instant;

import com.devsu.domain.entity.Account;
import com.devsu.domain.entity.Movement;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MovementMother {

  public static final String MOVEMENT_TYPE = "Retiro";

  public static final Integer MOVEMENT_ID = 1;

  public static final Instant TIMESTAMP = Instant.now();

  public static final BigDecimal VALUE = new BigDecimal(1000);

  public static final BigDecimal INITIAL_BALANCE = new BigDecimal(500);

  public static Movement.MovementBuilder builder() {
    return Movement.builder()
        .account(AccountMother.complete())
        .timestamp(TIMESTAMP)
        .movementType(MOVEMENT_TYPE)
        .value(VALUE)
        .initialBalance(INITIAL_BALANCE);
  }

  public static Movement complete() {
    return builder().build();
  }

  public static Movement withAccount(final Account account) {
    return builder().account(account).build();
  }

}

