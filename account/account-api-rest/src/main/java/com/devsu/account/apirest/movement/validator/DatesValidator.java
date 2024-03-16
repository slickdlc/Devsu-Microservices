package com.devsu.account.apirest.movement.validator;

import com.devsu.account.apirest.movement.dto.MovementsByCustomerRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DatesValidator implements ConstraintValidator<ValidDates, MovementsByCustomerRequestDto> {

  @Override
  public boolean isValid(MovementsByCustomerRequestDto requestDto,
      ConstraintValidatorContext constraintValidatorContext) {
    if (requestDto.getStartDate() == null
        || requestDto.getEndDate() == null) {
      return false;
    }

    return requestDto.getStartDate().isBefore(requestDto.getEndDate());
  }

}