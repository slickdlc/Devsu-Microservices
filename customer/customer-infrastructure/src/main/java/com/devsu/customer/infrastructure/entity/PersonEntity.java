package com.devsu.customer.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class PersonEntity {

  protected String name;

  protected String gender;

  protected Integer age;

  @Column(unique = true)
  protected String identification;

  protected String address;

  protected String phone;

}
