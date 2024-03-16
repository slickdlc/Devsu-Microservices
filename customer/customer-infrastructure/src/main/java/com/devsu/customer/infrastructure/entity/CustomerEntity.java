package com.devsu.customer.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class CustomerEntity extends PersonEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer customerId;

  private String password;

  private boolean active;

  @Builder
  public CustomerEntity(final String name, final String gender,
      final Integer age, final String identification, final String address, final String phone,
      final Integer customerId, final String password, final boolean active) {
    super(name, gender, age, identification, address, phone);
    this.customerId = customerId;
    this.password = password;
    this.active = active;
  }

}
