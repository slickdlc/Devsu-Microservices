package com.devsu.account.apirest.movement.validator;

import com.devsu.account.apirest.movement.dto.MovementRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccountValidator implements ConstraintValidator<ValidAccount, MovementRequestDto> {

  @Override
  public boolean isValid(MovementRequestDto movementRequestDto,
      ConstraintValidatorContext constraintValidatorContext) {
    
    return isAccountIdValid(movementRequestDto) && !isAccountNumberValid(movementRequestDto)
        || (!isAccountIdValid(movementRequestDto) && isAccountNumberValid(movementRequestDto));
  }
  
  private boolean isAccountIdValid(MovementRequestDto movementRequestDto) {
    return movementRequestDto.getCuentaId() != null;
  }
  
  private boolean isAccountNumberValid(MovementRequestDto movementRequestDto) {
    return movementRequestDto.getNumeroDeCuenta() != null &&
        movementRequestDto.getNumeroDeCuenta().length() == 16;
  }
  
}