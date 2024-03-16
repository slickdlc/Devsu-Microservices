package com.devsu.customer.apirest.common.validator;

import java.util.Arrays;

import com.devsu.customer.apirest.customer.dto.CustomerGenderEnumDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomerGenderValidator implements ConstraintValidator<ValidCustomerGender, String> {

  private String[] subset;

  @Override
  public void initialize(ValidCustomerGender constraint) {
    this.subset = Arrays.stream(constraint.anyOf()).map(CustomerGenderEnumDto::getValue).toArray(String[]::new);
  }

  @Override
  public boolean isValid(final String value, ConstraintValidatorContext context) {
    return value == null || Arrays.asList(subset).contains(value);
  }
}
