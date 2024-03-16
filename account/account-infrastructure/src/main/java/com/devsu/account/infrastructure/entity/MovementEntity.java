package com.devsu.account.infrastructure.entity;

import java.math.BigDecimal;
import java.time.Instant;

import com.devsu.domain.entity.Movement;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MovementEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer movementId;
  
  @ManyToOne
  private AccountEntity account;

  private Instant timestamp;

  private String movementType;

  private BigDecimal value;

  private BigDecimal initialBalance;

  //Auditoría
  private Instant createdAt;
}
