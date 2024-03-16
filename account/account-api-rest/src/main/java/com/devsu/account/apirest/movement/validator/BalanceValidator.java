package com.devsu.account.apirest.movement.validator;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BalanceValidator implements ConstraintValidator<ValidBalance, BigDecimal> {

  @Override
  public boolean isValid(BigDecimal balance,
      ConstraintValidatorContext constraintValidatorContext) {
    return Objects.nonNull(balance) && !balance.equals(BigDecimal.ZERO);
  }
}