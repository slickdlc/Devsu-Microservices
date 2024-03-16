package com.devsu.entity;

import java.math.BigDecimal;

import com.devsu.domain.entity.Account;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMother {

  public static final String ACCOUNT_TYPE = "Corriente";

  public static final String ACCOUNT_NUMBER = "4000300010002000";

  public static final Integer ACCOUNT_ID = 1;

  public static final BigDecimal INITIAL_BALANCE = new BigDecimal(1000);

  public static final BigDecimal CURRENT_BALANCE = new BigDecimal(500);

  public static final boolean ACTIVE = true;

  public static Account.AccountBuilder builder() {
    return Account.builder()
        .accountId(ACCOUNT_ID)
        .customerId(CustomerMother.CUSTOMER_ID)
        .accountNumber(ACCOUNT_NUMBER)
        .accountType(ACCOUNT_TYPE)
        .initialBalance(INITIAL_BALANCE)
        .currentBalance(CURRENT_BALANCE)
        .active(ACTIVE);
  }

  public static Account complete() {
    return builder().build();
  }

  public static Account withAccountId(final Integer accountId) {
    return builder().accountId(accountId).build();
  }

  public static Account withActive(final boolean active) {
    return builder().active(active).build();
  }

}
