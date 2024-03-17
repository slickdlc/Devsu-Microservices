package com.devsu.account.apirest.movement.dto;

import lombok.Getter;

@Getter
public enum MovementTypeEnum {
  DEPOSIT("Depósito"),
  WITHDRAWAL("Retiro");

  private final String description;

  MovementTypeEnum(final String description) {
    this.description = description;
  }
}
