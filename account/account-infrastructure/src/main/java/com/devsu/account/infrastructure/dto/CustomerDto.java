package com.devsu.account.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

  private Integer customerId;

  private String name;

  private String gender;

  private Integer age;

  private String identification;

  private String address;

  private String phone;

  private String password;

  private boolean active;

}