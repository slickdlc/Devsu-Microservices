package com.devsu.customer.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Customer extends Person {

  private Integer customerId;

  private String password;

  private boolean active;

  @Builder
  public Customer(final Integer personId, final String name, final String gender,
      final Integer age, final String identification, final String address, final String phone,
      final Integer customerId, final String password, final boolean active) {
    super(personId, name, gender, age, identification, address, phone);
    this.customerId = customerId;
    this.password = password;
    this.active = active;
  }

}
