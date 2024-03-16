package com.devsu.account.apirest.account.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {

  private Integer accountId;

  private Integer customerId;

  private String accountNumber;

  private String accountType;

  private BigDecimal currentBalance;

  private boolean active;

}
