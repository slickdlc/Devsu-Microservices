package com.devsu.account.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.devsu.account.infrastructure.entity.AccountEntityMother;
import com.devsu.account.infrastructure.mapper.AccountEntityMapperImpl;
import com.devsu.entity.AccountMother;
import com.devsu.entity.CustomerMother;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.orm.jpa.JpaSystemException;

@ExtendWith(MockitoExtension.class)
class AccountRepositoryImplTest {

  @InjectMocks
  private AccountRepositoryImpl accountRepository;

  @Mock
  private AccountJpaRepository accountJpaRepository;

  @Spy
  private AccountEntityMapperImpl accountMapper;

  @Nested
  class SaveAccount {

    @Test
    void given_negativeCase_when_saveAccount_then_throwException() {
      doThrow(JpaSystemException.class).when(AccountRepositoryImplTest.this.accountJpaRepository).save(any());

      assertThrows(JpaSystemException.class, () -> AccountRepositoryImplTest.this.accountRepository.saveAccount(AccountMother.complete()));
    }

    @Test
    void given_positiveCase_when_saveAccount_then_returnAccount() {
      assertDoesNotThrow(() -> AccountRepositoryImplTest.this.accountRepository.saveAccount(AccountMother.complete()));

      verify(AccountRepositoryImplTest.this.accountJpaRepository).save(any());
    }
  }

  @Nested
  class DeleteAccountById {

    @Test
    void given_negativeCase_when_deleteAccountById_then_throwException() {
      doThrow(JpaSystemException.class).when(AccountRepositoryImplTest.this.accountJpaRepository).deleteById(any());

      assertThrows(JpaSystemException.class,
          () -> AccountRepositoryImplTest.this.accountRepository.deleteAccountById(AccountMother.ACCOUNT_ID));
    }

    @Test
    void given_positiveCase_when_deleteAccountById_then_returnAccount() {
      assertDoesNotThrow(() -> AccountRepositoryImplTest.this.accountRepository.deleteAccountById(AccountMother.ACCOUNT_ID));

      verify(AccountRepositoryImplTest.this.accountJpaRepository).deleteById(any());
    }
  }

  @Nested
  class ReadAccounts {

    @Test
    void given_negativeCase_when_readAccounts_then_throwException() {
      doThrow(JpaSystemException.class).when(AccountRepositoryImplTest.this.accountJpaRepository).findAll();

      assertThrows(JpaSystemException.class, () -> AccountRepositoryImplTest.this.accountRepository.readAccounts());
    }

    @Test
    void given_positiveCase_when_readAccounts_then_returnAccounts() {
      when(AccountRepositoryImplTest.this.accountJpaRepository.findAll()).thenReturn(List.of(AccountEntityMother.complete()));

      final var result = AccountRepositoryImplTest.this.accountRepository.readAccounts();

      assertNotNull(result);
      assertFalse(result.isEmpty());
      assertEquals(CustomerMother.CUSTOMER_ID, result.get(0).getCustomerId());
      assertEquals(AccountMother.ACCOUNT_ID, result.get(0).getAccountId());
      assertEquals(AccountMother.ACCOUNT_NUMBER, result.get(0).getAccountNumber());
      assertEquals(AccountMother.ACCOUNT_TYPE, result.get(0).getAccountType());
      assertEquals(AccountMother.INITIAL_BALANCE, result.get(0).getInitialBalance());
      assertEquals(AccountMother.CURRENT_BALANCE, result.get(0).getCurrentBalance());
      assertEquals(AccountMother.ACTIVE, result.get(0).isActive());
      verify(AccountRepositoryImplTest.this.accountJpaRepository).findAll();
    }
  }

  @Nested
  class FindByAccountId {

    @Test
    void given_negativeCase_when_findByAccountId_then_throwException() {
      doThrow(JpaSystemException.class).when(AccountRepositoryImplTest.this.accountJpaRepository).findById(any());

      assertThrows(JpaSystemException.class,
          () -> AccountRepositoryImplTest.this.accountRepository.findByAccountId(AccountMother.ACCOUNT_ID));
    }

    @Test
    void given_positiveCase_when_findByAccountId_then_returnAccount() {
      when(AccountRepositoryImplTest.this.accountJpaRepository.findById(any())).thenReturn(Optional.of(AccountEntityMother.complete()));

      final var result = AccountRepositoryImplTest.this.accountRepository.findByAccountId(AccountMother.ACCOUNT_ID);

      assertNotNull(result);
      assertTrue(result.isPresent());
      assertEquals(CustomerMother.CUSTOMER_ID, result.get().getCustomerId());
      assertEquals(AccountMother.ACCOUNT_ID, result.get().getAccountId());
      assertEquals(AccountMother.ACCOUNT_NUMBER, result.get().getAccountNumber());
      assertEquals(AccountMother.ACCOUNT_TYPE, result.get().getAccountType());
      assertEquals(AccountMother.INITIAL_BALANCE, result.get().getInitialBalance());
      assertEquals(AccountMother.CURRENT_BALANCE, result.get().getCurrentBalance());
      assertEquals(AccountMother.ACTIVE, result.get().isActive());
      verify(AccountRepositoryImplTest.this.accountJpaRepository).findById(any());
    }
  }

  @Nested
  class FindByAccountNumber {

    @Test
    void given_negativeCase_when_findByAccountNumber_then_throwException() {
      doThrow(JpaSystemException.class).when(AccountRepositoryImplTest.this.accountJpaRepository).findByAccountNumber(any());

      assertThrows(JpaSystemException.class,
          () -> AccountRepositoryImplTest.this.accountRepository.findByAccountNumber(AccountMother.ACCOUNT_NUMBER));
    }

    @Test
    void given_positiveCase_when_findByAccountNumber_then_returnAccount() {
      when(AccountRepositoryImplTest.this.accountJpaRepository.findByAccountNumber(any())).thenReturn(
          Optional.of(AccountEntityMother.complete()));

      final var result = AccountRepositoryImplTest.this.accountRepository.findByAccountNumber(AccountMother.ACCOUNT_NUMBER);

      assertNotNull(result);
      assertTrue(result.isPresent());
      assertEquals(CustomerMother.CUSTOMER_ID, result.get().getCustomerId());
      assertEquals(AccountMother.ACCOUNT_ID, result.get().getAccountId());
      assertEquals(AccountMother.ACCOUNT_NUMBER, result.get().getAccountNumber());
      assertEquals(AccountMother.ACCOUNT_TYPE, result.get().getAccountType());
      assertEquals(AccountMother.INITIAL_BALANCE, result.get().getInitialBalance());
      assertEquals(AccountMother.CURRENT_BALANCE, result.get().getCurrentBalance());
      assertEquals(AccountMother.ACTIVE, result.get().isActive());
      verify(AccountRepositoryImplTest.this.accountJpaRepository).findByAccountNumber(any());
    }
  }

  @Nested
  class FindActiveAccountIds {

    @Test
    void given_negativeCase_when_findActiveAccountIds_then_throwException() {
      doThrow(JpaSystemException.class).when(AccountRepositoryImplTest.this.accountJpaRepository).findAllByCustomerId(any());

      assertThrows(JpaSystemException.class,
          () -> AccountRepositoryImplTest.this.accountRepository.findActiveAccountIds(CustomerMother.CUSTOMER_ID));
    }

    @Test
    void given_positiveCase_when_findActiveAccountIds_then_returnAccountIds() {
      when(AccountRepositoryImplTest.this.accountJpaRepository.findAllByCustomerId(any())).thenReturn(
          List.of(AccountEntityMother.complete()));

      final var result = AccountRepositoryImplTest.this.accountRepository.findActiveAccountIds(CustomerMother.CUSTOMER_ID);

      assertNotNull(result);
      assertFalse(result.isEmpty());
      assertEquals(AccountMother.ACCOUNT_ID, result.get(0));
      verify(AccountRepositoryImplTest.this.accountJpaRepository).findAllByCustomerId(any());
    }
  }
}