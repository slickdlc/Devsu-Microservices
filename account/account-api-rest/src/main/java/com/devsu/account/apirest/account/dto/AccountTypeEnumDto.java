package com.devsu.account.apirest.account.dto;

import lombok.Getter;

@Getter
public enum AccountTypeEnumDto {
  CORRIENTE("Corriente"),
  AHORROS("Ahorros");

  private final String value;

  AccountTypeEnumDto(final String value) {
    this.value = value;
  }

  public static AccountTypeEnumDto fromString(final String value) {
    if (value.equalsIgnoreCase("Corriente")) {
      return CORRIENTE;
    } else if (value.equalsIgnoreCase("Ahorros")) {
      return AHORROS;
    }
    throw new IllegalArgumentException("Invalid value");
  }
}
