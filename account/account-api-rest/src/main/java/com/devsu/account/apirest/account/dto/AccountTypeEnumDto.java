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
}
