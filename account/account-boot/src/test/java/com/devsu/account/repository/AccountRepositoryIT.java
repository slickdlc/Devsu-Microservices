package com.devsu.account.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import com.devsu.entity.AccountMother;
import com.devsu.account.config.ApplicationAbstractIT;
import com.devsu.domain.entity.Account;
import com.devsu.domain.repository.AccountRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountRepositoryIT extends ApplicationAbstractIT {

  private static final Integer ACCOUNT_ID_THAT_EXISTS = 1;

  private static final Integer CUSTOMER_ID = 1;

  private static final String ACCOUNT_NUMBER_THAT_EXISTS = "1234567812345678";

  private static final String ACCOUNT_TYPE = "Ahorros";

  private static final BigDecimal INITIAL_BALANCE = new BigDecimal("1000000");

  private static final BigDecimal CURRENT_BALANCE = new BigDecimal("1000000");

  private static final Boolean ACTIVE = true;

  @Autowired
  private AccountRepository accountRepository;

  private void validateExpectAccount(final Account account) {
    assertEquals(ACCOUNT_ID_THAT_EXISTS, account.getAccountId());
    assertEquals(CUSTOMER_ID, account.getCustomerId());
    assertEquals(ACCOUNT_NUMBER_THAT_EXISTS, account.getAccountNumber());
    assertEquals(ACCOUNT_TYPE, account.getAccountType());
    assertEquals(INITIAL_BALANCE, account.getInitialBalance());
    assertEquals(CURRENT_BALANCE, account.getCurrentBalance());
    assertEquals(ACTIVE, account.isActive());

  }

  @Nested
  class SaveAccount {

    private static final String ACCOUNT_NUMBER_DOES_NOT_EXIST = "12341234";

    private static final String ACCOUNT_NUMBER_DOES_NOT_EXIST_TWO = "12341235";

    private static final Integer ACCOUNT_ID_TO_UPDATE = 3;

    @Test
    void given_accountToCreate_when_saveAccount_then_accountIsCreated() {
      final Account account = AccountMother.builder().accountId(null).accountNumber(ACCOUNT_NUMBER_DOES_NOT_EXIST).build();

      AccountRepositoryIT.this.accountRepository.saveAccount(account);
      final var accountCreated = AccountRepositoryIT.this.accountRepository.findByAccountNumber(ACCOUNT_NUMBER_DOES_NOT_EXIST);

      assertNotNull(accountCreated);
      assertTrue(accountCreated.isPresent());
      assertNotNull(accountCreated.get().getAccountId());
      assertEquals(ACCOUNT_NUMBER_DOES_NOT_EXIST, accountCreated.get().getAccountNumber());
    }

    @Test
    void given_accountToUpdate_when_saveAccount_then_accountIsUpdated() {
      final Account account =
          AccountMother.builder().accountId(ACCOUNT_ID_TO_UPDATE).accountNumber(ACCOUNT_NUMBER_DOES_NOT_EXIST_TWO).build();

      AccountRepositoryIT.this.accountRepository.saveAccount(account);
      final var accountUpdated = AccountRepositoryIT.this.accountRepository.findByAccountNumber(ACCOUNT_NUMBER_DOES_NOT_EXIST_TWO);

      assertNotNull(accountUpdated);
      assertTrue(accountUpdated.isPresent());
      assertEquals(ACCOUNT_ID_TO_UPDATE, accountUpdated.get().getAccountId());
      assertEquals(ACCOUNT_NUMBER_DOES_NOT_EXIST_TWO, accountUpdated.get().getAccountNumber());
    }
  }

  @Nested
  class DeleteAccount {

    private static final Integer ACCOUNT_ID_TO_DELETE = 2;

    @Test
    void given_validAccountId_when_deleteAccount_then_accountIsDeleted() {
      final var accountBeforeDelete = AccountRepositoryIT.this.accountRepository.findByAccountId(ACCOUNT_ID_TO_DELETE);

      AccountRepositoryIT.this.accountRepository.deleteAccountById(ACCOUNT_ID_TO_DELETE);

      final var accountDeleted = AccountRepositoryIT.this.accountRepository.findByAccountId(ACCOUNT_ID_TO_DELETE);

      assertNotNull(accountBeforeDelete);
      assertTrue(accountBeforeDelete.isPresent());
      assertNotNull(accountDeleted);
      assertTrue(accountDeleted.isEmpty());
    }
  }

  @Nested
  class ReadAccounts {

    @Test
    void given_nothing_when_readAccounts_then_returnAccounts() {
      final var accounts = AccountRepositoryIT.this.accountRepository.readAccounts();

      assertNotNull(accounts);
      assertTrue(accounts.size() > 1);
      final var optionalAccount = accounts.stream().filter(c -> ACCOUNT_ID_THAT_EXISTS.equals(c.getAccountId())).findFirst();
      assertTrue(optionalAccount.isPresent());
      final var account = optionalAccount.get();
      validateExpectAccount(account);
    }
  }

  @Nested
  class FindAccountById {

    @Test
    void given_idThatExists_when_findAccountById_then_returnAccounts() {
      final var account = AccountRepositoryIT.this.accountRepository.findByAccountId(ACCOUNT_ID_THAT_EXISTS);

      assertNotNull(account);
      assertTrue(account.isPresent());
      validateExpectAccount(account.get());
    }
  }

  @Nested
  class FindAccountByAccountNumber {

    @Test
    void given_accountNumberThatExists_when_findAccountByAccountNumber_then_returnAccounts() {
      final var account = AccountRepositoryIT.this.accountRepository.findByAccountNumber(ACCOUNT_NUMBER_THAT_EXISTS);

      assertNotNull(account);
      assertTrue(account.isPresent());
      validateExpectAccount(account.get());
    }
  }
}