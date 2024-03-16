package com.devsu.account.infrastructure.entity;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer accountId;

  private Integer customerId;

  private String accountNumber;

  private String accountType;

  private BigDecimal initialBalance;

  private BigDecimal currentBalance;

  private boolean active;
  
  //Audit
  private Instant updatedAt;
}
