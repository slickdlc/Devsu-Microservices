package com.devsu.domain.entity;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movement {

  private Account account;

  private Instant timestamp;

  private String movementType;

  private BigDecimal initialBalance;

  private BigDecimal value;

}
