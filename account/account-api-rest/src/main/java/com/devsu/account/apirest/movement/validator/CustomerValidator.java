package com.devsu.account.apirest.movement.validator;

import com.devsu.account.apirest.movement.dto.MovementsByCustomerRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomerValidator implements ConstraintValidator<ValidCustomer, MovementsByCustomerRequestDto> {

  @Override
  public boolean isValid(MovementsByCustomerRequestDto movementRequestDto,
      ConstraintValidatorContext constraintValidatorContext) {

    return isCustomerIdValid(movementRequestDto) && !isCustomerIdentificationValid(movementRequestDto)
        || (!isCustomerIdValid(movementRequestDto) && isCustomerIdentificationValid(movementRequestDto));
  }

  private boolean isCustomerIdValid(MovementsByCustomerRequestDto movementRequestDto) {
    return movementRequestDto.getCustomerId() != null;
  }

  private boolean isCustomerIdentificationValid(MovementsByCustomerRequestDto movementRequestDto) {
    return movementRequestDto.getCustomerIdentification() != null &&
        movementRequestDto.getCustomerIdentification().length() == 8;
  }

}