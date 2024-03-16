package com.devsu.customer.apirest.customer.dto;

import lombok.Getter;

@Getter
public enum CustomerGenderEnumDto {
  FEMALE("F"),
  MALE("M");

  private final String value;

  private CustomerGenderEnumDto(final String value) {
    this.value = value;
  }

  public static CustomerGenderEnumDto fromString(final String value) {
    if (value.equals("F")) {
      return FEMALE;
    } else if (value.equals("M")) {
      return MALE;
    }
    throw new IllegalArgumentException("Invalid value");
  }
}
