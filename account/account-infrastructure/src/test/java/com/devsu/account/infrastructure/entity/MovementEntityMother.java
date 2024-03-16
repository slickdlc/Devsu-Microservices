package com.devsu.account.infrastructure.entity;

import java.time.Instant;

import com.devsu.entity.MovementMother;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MovementEntityMother {

  private static final Instant CREATED_AT = Instant.now();

  public static MovementEntity.MovementEntityBuilder builder() {
    return MovementEntity.builder()
        .movementId(MovementMother.MOVEMENT_ID)
        .account(AccountEntityMother.complete())
        .timestamp(MovementMother.TIMESTAMP)
        .movementType(MovementMother.MOVEMENT_TYPE)
        .value(MovementMother.VALUE)
        .initialBalance(MovementMother.INITIAL_BALANCE)
        .createdAt(CREATED_AT);
  }

  public static MovementEntity complete() {
    return builder().build();
  }
}
