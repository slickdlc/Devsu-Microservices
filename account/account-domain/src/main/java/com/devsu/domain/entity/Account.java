package com.devsu.domain.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

  private Integer accountId;

  private Integer customerId;

  private String accountNumber;

  private String accountType;

  private BigDecimal initialBalance;

  private BigDecimal currentBalance;

  private boolean active;

}
