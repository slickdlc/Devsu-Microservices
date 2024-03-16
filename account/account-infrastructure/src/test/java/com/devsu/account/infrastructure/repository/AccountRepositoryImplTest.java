package com.devsu.account.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.devsu.AccountMother;
import com.devsu.account.infrastructure.entity.AccountEntityMother;
import com.devsu.account.infrastructure.mapper.AccountEntityMapperImpl;
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
    void given_negativeCase_when_saveAccount_then_throwsException() {
      when(AccountRepositoryImplTest.this.accountJpaRepository.save(any())).thenThrow(JpaSystemException.class);

      assertThrows(JpaSystemException.class,
          () -> AccountRepositoryImplTest.this.accountRepository.saveAccount(AccountMother.complete()));
    }

    @Test
    void given_positiveCase_when_saveAccount_then_returnAccount() {
      AccountRepositoryImplTest.this.accountRepository.saveAccount(AccountMother.complete());

      verify(AccountRepositoryImplTest.this.accountJpaRepository).save(any());
    }
  }

  @Nested
  class DeleteAccountById {

    @Test
    void given_negativeCase_when_deleteAccountById_then_throwsException() {
      doThrow(JpaSystemException.class).when(AccountRepositoryImplTest.this.accountJpaRepository).deleteById(any());

      assertThrows(JpaSystemException.class,
          () -> AccountRepositoryImplTest.this.accountRepository.deleteAccountById(AccountMother.ACCOUNT_ID));

      verify(AccountRepositoryImplTest.this.accountJpaRepository).deleteById(AccountMother.ACCOUNT_ID);
    }

    @Test
    void given_positiveCase_when_deleteAccountById_then_returnAccount() {
      doNothing().when(AccountRepositoryImplTest.this.accountJpaRepository).deleteById(any());

      AccountRepositoryImplTest.this.accountRepository.deleteAccountById(AccountMother.ACCOUNT_ID);

      verify(AccountRepositoryImplTest.this.accountJpaRepository).deleteById(AccountMother.ACCOUNT_ID);
    }
  }

  @Nested
  class ReadAccounts {

    @Test
    void given_negativeCase_when_readAccounts_then_throwsException() {
      when(AccountRepositoryImplTest.this.accountJpaRepository.findAll()).thenThrow(JpaSystemException.class);

      assertThrows(JpaSystemException.class, () -> AccountRepositoryImplTest.this.accountRepository.readAccounts());

      verify(AccountRepositoryImplTest.this.accountJpaRepository).findAll();
    }

    @Test
    void given_positiveCase_when_readAccounts_then_returnAccounts() {
      when(AccountRepositoryImplTest.this.accountJpaRepository.findAll()).thenReturn(List.of(AccountEntityMother.complete()));

      AccountRepositoryImplTest.this.accountRepository.readAccounts();

      verify(AccountRepositoryImplTest.this.accountJpaRepository).findAll();
    }
  }

  @Nested
  class FindAccountById {

    @Test
    void given_negativeCase_when_findAccountById_then_throwsException() {
      when(AccountRepositoryImplTest.this.accountJpaRepository.findById(AccountMother.ACCOUNT_ID)).thenThrow(JpaSystemException.class);

      assertThrows(JpaSystemException.class,
          () -> AccountRepositoryImplTest.this.accountRepository.findAccountById(AccountMother.ACCOUNT_ID));

      verify(AccountRepositoryImplTest.this.accountJpaRepository).findById(AccountMother.ACCOUNT_ID);
    }

    @Test
    void given_positiveCase_when_findAccountById_then_returnAccount() {
      when(AccountRepositoryImplTest.this.accountJpaRepository.findById(AccountMother.ACCOUNT_ID)).thenReturn(
          java.util.Optional.of(AccountEntityMother.complete()));

      AccountRepositoryImplTest.this.accountRepository.findAccountById(AccountMother.ACCOUNT_ID);

      verify(AccountRepositoryImplTest.this.accountJpaRepository).findById(AccountMother.ACCOUNT_ID);
    }
  }

  @Nested
  class FindAccountByAccountNumber {

    @Test
    void given_negativeCase_when_findAccountByAccountNumber_then_throwsException() {
      when(AccountRepositoryImplTest.this.accountJpaRepository.findByAccountNumber(AccountMother.ACCOUNT_NUMBER)).thenThrow(
          JpaSystemException.class);

      assertThrows(JpaSystemException.class,
          () -> AccountRepositoryImplTest.this.accountRepository.findByAccountNumber(AccountMother.ACCOUNT_NUMBER));

      verify(AccountRepositoryImplTest.this.accountJpaRepository).findByAccountNumber(AccountMother.ACCOUNT_NUMBER);
    }

    @Test
    void given_positiveCase_when_findAccountByAccountNumber_then_returnAccount() {
      when(AccountRepositoryImplTest.this.accountJpaRepository.findByAccountNumber(AccountMother.ACCOUNT_NUMBER)).thenReturn(
          java.util.Optional.of(AccountEntityMother.complete()));

      AccountRepositoryImplTest.this.accountRepository.findByAccountNumber(AccountMother.ACCOUNT_NUMBER);

      verify(AccountRepositoryImplTest.this.accountJpaRepository).findByAccountNumber(AccountMother.ACCOUNT_NUMBER);
    }
  }

}