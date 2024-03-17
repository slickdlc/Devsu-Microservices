package com.devsu.account.apirest.movement.validator;

import java.time.format.DateTimeParseException;

import com.devsu.account.apirest.movement.mapper.MovementMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateFormatValidator implements ConstraintValidator<ValidDateFormat, String> {

  @Override
  public boolean isValid(final String date,
      ConstraintValidatorContext constraintValidatorContext) {
    if (date == null) {
      return true;
    }
    try {
      MovementMapper.FORMATTER.parse(date);
      return true;
    } catch (final DateTimeParseException e) {
      return false;
    }
  }

}