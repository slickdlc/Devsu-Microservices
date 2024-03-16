package com.devsu.entity;

import static com.devsu.entity.CustomerMother.CUSTOMER_NAME;

import com.devsu.domain.entity.CustomerMovement;
import com.devsu.domain.entity.Movement;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerMovementMother {

  public static CustomerMovement.CustomerMovementBuilder builder() {
    return CustomerMovement.builder().movement(MovementMother.complete()).customerName(CUSTOMER_NAME);
  }

  public static CustomerMovement complete() {
    return builder().build();
  }

  public static CustomerMovement withMovement(final Movement movement) {
    return builder().movement(movement).build();
  }
}
