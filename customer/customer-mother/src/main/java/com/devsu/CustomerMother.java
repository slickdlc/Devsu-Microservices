package com.devsu;

import com.devsu.customer.domain.entity.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerMother {

  public static final Integer PERSON_ID = 1;

  public static final String NAME = "John Doe";

  public static final String GENDER = "M";

  public static final Integer AGE = 30;

  public static final String IDENTIFICATION = "12345678";

  public static final String ADDRESS = "123 Main St";

  public static final String PHONE = "123456789";

  public static final Integer CUSTOMER_ID = 1;

  public static final String PASSWORD = "password";

  public static final boolean ACTIVE = true;

  public static Customer.CustomerBuilder builder() {
    return Customer.builder()
        .personId(PERSON_ID)
        .name(NAME)
        .gender(GENDER)
        .age(AGE)
        .identification(IDENTIFICATION)
        .address(ADDRESS)
        .phone(PHONE)
        .customerId(CUSTOMER_ID)
        .password(PASSWORD)
        .active(ACTIVE);
  }

  public static Customer complete() {
    return builder().build();
  }
  
  public static Customer withCustomerId(final Integer customerId){
    return builder().customerId(customerId).build();
  }
}
