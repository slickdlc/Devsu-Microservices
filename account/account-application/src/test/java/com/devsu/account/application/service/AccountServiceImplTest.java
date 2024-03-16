package com.devsu.account.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.devsu.AccountMother;
import com.devsu.domain.exception.ServiceException;
import com.devsu.domain.repository.AccountRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

  @InjectMocks
  private AccountServiceImpl accountService;

  @Mock
  private AccountRepository accountRepository;

  @Nested
  class CreateAccount {

    @Test
    void given_accountWithId_when_createAccount_then_expectedException() {
      final var account = AccountMother.complete();

      assertThrows(ServiceException.class, () -> AccountServiceImplTest.this.accountService.createAccount(account));

      verify(AccountServiceImplTest.this.accountRepository, never()).findByAccountNumber(account.getAccountNumber());
      verify(AccountServiceImplTest.this.accountRepository, never()).saveAccount(account);
    }

    @Test
    void given_accountWithAccountNumberThatExists_when_createAccount_then_expectedException() {
      final var account = AccountMother.withAccountId(null);

      when(AccountServiceImplTest.this.accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(
          Optional.of(AccountMother.complete()));

      assertThrows(ServiceException.class, () -> AccountServiceImplTest.this.accountService.createAccount(account));

      verify(AccountServiceImplTest.this.accountRepository).findByAccountNumber(account.getAccountNumber());
      verify(AccountServiceImplTest.this.accountRepository, never()).saveAccount(account);
    }

    @Test
    void given_positiveCase_when_createAccount_then_accountIsCreated() {
      final var account = AccountMother.withAccountId(null);

      when(AccountServiceImplTest.this.accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(
          Optional.empty());

      AccountServiceImplTest.this.accountService.createAccount(account);

      verify(AccountServiceImplTest.this.accountRepository).findByAccountNumber(account.getAccountNumber());
      verify(AccountServiceImplTest.this.accountRepository).saveAccount(account);
    }
  }

  @Nested
  class UpdateAccount {

    @Test
    void given_accountWithIdThatDoesNotExists_when_updateAccount_then_expectedException() {
      final var account = AccountMother.withAccountId(9999);

      when(AccountServiceImplTest.this.accountRepository.findAccountById(account.getAccountId())).thenReturn(Optional.empty());

      assertThrows(ServiceException.class, () -> AccountServiceImplTest.this.accountService.updateAccount(account));

      verify(AccountServiceImplTest.this.accountRepository).findAccountById(account.getAccountId());
      verify(AccountServiceImplTest.this.accountRepository, never()).findByAccountNumber(account.getAccountNumber());
      verify(AccountServiceImplTest.this.accountRepository, never()).saveAccount(account);
    }

    @Test
    void given_accountWithAccountNumberThatExists_when_updateAccount_then_expectedException() {
      final var account = AccountMother.withAccountId(null);

      when(AccountServiceImplTest.this.accountRepository.findAccountById(account.getAccountId())).thenReturn(
          Optional.of(AccountMother.complete()));
      when(AccountServiceImplTest.this.accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(
          Optional.of(AccountMother.complete()));

      assertThrows(ServiceException.class, () -> AccountServiceImplTest.this.accountService.updateAccount(account));

      verify(AccountServiceImplTest.this.accountRepository).findAccountById(account.getAccountId());
      verify(AccountServiceImplTest.this.accountRepository).findByAccountNumber(account.getAccountNumber());
      verify(AccountServiceImplTest.this.accountRepository, never()).saveAccount(account);
    }

    @Test
    void given_positiveCase_when_updateAccount_then_accountIsUpdated() {
      final var account = AccountMother.withAccountId(null);

      when(AccountServiceImplTest.this.accountRepository.findAccountById(account.getAccountId())).thenReturn(
          Optional.of(AccountMother.complete()));
      when(AccountServiceImplTest.this.accountRepository.findByAccountNumber(account.getAccountNumber())).thenReturn(
          Optional.empty());

      AccountServiceImplTest.this.accountService.updateAccount(account);

      verify(AccountServiceImplTest.this.accountRepository).findAccountById(account.getAccountId());
      verify(AccountServiceImplTest.this.accountRepository).findByAccountNumber(account.getAccountNumber());
      verify(AccountServiceImplTest.this.accountRepository).saveAccount(account);
    }
  }

  @Nested
  class DeleteAccount {

    @Test
    void given_accountIdThatNotExists_when_deleteAccount_then_expectedException() {
      when(AccountServiceImplTest.this.accountRepository.findAccountById(AccountMother.ACCOUNT_ID)).thenReturn(Optional.empty());

      assertThrows(ServiceException.class, () -> AccountServiceImplTest.this.accountService.deleteAccount(AccountMother.ACCOUNT_ID));

      verify(AccountServiceImplTest.this.accountRepository).findAccountById(AccountMother.ACCOUNT_ID);
      verify(AccountServiceImplTest.this.accountRepository, never()).deleteAccountById(AccountMother.ACCOUNT_ID);
    }

    @Test
    void given_positiveCase_when_deleteAccount_then_accountIsDeleted() {
      when(AccountServiceImplTest.this.accountRepository.findAccountById(AccountMother.ACCOUNT_ID)).thenReturn(
          Optional.of(AccountMother.complete()));

      AccountServiceImplTest.this.accountService.deleteAccount(AccountMother.ACCOUNT_ID);

      verify(AccountServiceImplTest.this.accountRepository).findAccountById(AccountMother.ACCOUNT_ID);
      verify(AccountServiceImplTest.this.accountRepository).deleteAccountById(AccountMother.ACCOUNT_ID);
    }
  }

  @Nested
  class GetAllAccounts {

    @Test
    void given_positiveCase_when_getAllAccounts_then_expectedAccounts() {
      when(AccountServiceImplTest.this.accountRepository.readAccounts()).thenReturn(List.of(AccountMother.complete()));

      final var result = AccountServiceImplTest.this.accountService.getAllAccounts();

      assertNotNull(result);
      assertEquals(1, result.size());
      assertEquals(AccountMother.complete(), result.get(0));
      verify(AccountServiceImplTest.this.accountRepository).readAccounts();
    }
  }

  @Nested
  class GetAccountById {

    @Test
    void given_accountIdThatNotExists_when_getAccountById_then_expectedException() {
      when(AccountServiceImplTest.this.accountRepository.findAccountById(AccountMother.ACCOUNT_ID)).thenReturn(Optional.empty());

      assertThrows(ServiceException.class, () -> AccountServiceImplTest.this.accountService.getAccountById(AccountMother.ACCOUNT_ID));

      verify(AccountServiceImplTest.this.accountRepository).findAccountById(AccountMother.ACCOUNT_ID);
    }

    @Test
    void given_positiveCase_when_getAccountById_then_expectedAccount() {
      when(AccountServiceImplTest.this.accountRepository.findAccountById(AccountMother.ACCOUNT_ID)).thenReturn(
          Optional.of(AccountMother.complete()));

      final var result = AccountServiceImplTest.this.accountService.getAccountById(AccountMother.ACCOUNT_ID);

      assertNotNull(result);
      assertEquals(AccountMother.complete(), result);
      verify(AccountServiceImplTest.this.accountRepository).findAccountById(AccountMother.ACCOUNT_ID);
    }
  }
}