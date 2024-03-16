package com.devsu.customer.apirest.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

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
