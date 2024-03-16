package com.devsu.customer.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

  protected Integer personId;

  protected String name;

  protected String gender;

  protected Integer age;

  protected String identification;

  protected String address;

  protected String phone;

}
