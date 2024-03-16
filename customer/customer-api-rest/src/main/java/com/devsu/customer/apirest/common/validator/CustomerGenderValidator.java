package com.devsu.customer.apirest.common.validator;

import java.util.Arrays;

import com.devsu.customer.apirest.customer.dto.CustomerGenderEnumDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomerGenderSubSetValidator implements ConstraintValidator<CustomerGenderSubSet, String> {

  private String[] subset;

  @Override
  public void initialize(CustomerGenderSubSet constraint) {
    this.subset = Arrays.stream(constraint.anyOf()).map(CustomerGenderEnumDto::getValue).toArray(String[]::new);
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value == null || Arrays.asList(subset).contains(value);
  }
}
