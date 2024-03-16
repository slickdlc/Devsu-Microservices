package com.devsu.account.infrastructure.entity;

import java.time.Instant;

import com.devsu.entity.AccountMother;
import com.devsu.entity.CustomerMother;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountEntityMother {

  private static final Instant UPDATED_AT = Instant.now();

  public static AccountEntity.AccountEntityBuilder builder() {
    return AccountEntity.builder()
        .accountId(AccountMother.ACCOUNT_ID)
        .customerId(CustomerMother.CUSTOMER_ID)
        .accountNumber(AccountMother.ACCOUNT_NUMBER)
        .accountType(AccountMother.ACCOUNT_TYPE)
        .initialBalance(AccountMother.INITIAL_BALANCE)
        .currentBalance(AccountMother.CURRENT_BALANCE)
        .active(AccountMother.ACTIVE)
        .updatedAt(AccountEntityMother.UPDATED_AT);
  }

  public static AccountEntity complete() {
    return builder().build();
  }
}
