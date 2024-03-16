package com.devsu.account.infrastructure.entity;

import com.devsu.AccountMother;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountEntityMother {

  public AccountEntity.AccountEntityBuilder builder() {
    return AccountEntity.builder()
        .accountId(AccountMother.ACCOUNT_ID)
        .customerId(AccountMother.CUSTOMER_ID)
        .accountNumber(AccountMother.ACCOUNT_NUMBER)
        .accountType(AccountMother.ACCOUNT_NUMBER)
        .initialBalance(AccountMother.INITIAL_BALANCE)
        .currentBalance(AccountMother.CURRENT_BALANCE)
        .active(AccountMother.ACTIVE);
  }

  public AccountEntity complete() {
    return builder().build();
  }
}
