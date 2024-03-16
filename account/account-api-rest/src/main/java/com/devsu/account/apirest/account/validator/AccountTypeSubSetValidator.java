package com.devsu.account.apirest.account.validator;

import java.util.Arrays;

import com.devsu.account.apirest.account.dto.AccountTypeEnumDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AccountTypeSubSetValidator implements ConstraintValidator<AccountTypeSubSet, String> {

  private String[] subset;

  @Override
  public void initialize(AccountTypeSubSet constraint) {
    this.subset = Arrays.stream(constraint.anyOf()).map(AccountTypeEnumDto::getValue).toArray(String[]::new);
  }

  @Override
  public boolean isValid(final String value, ConstraintValidatorContext context) {
    return value == null || Arrays.asList(subset).contains(value);
  }
}
