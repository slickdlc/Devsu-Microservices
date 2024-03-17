package com.devsu.account.apirest.account.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.devsu.account.apirest.account.dto.AccountRequestDtoMother;
import com.devsu.entity.AccountMother;
import com.devsu.account.apirest.account.mapper.AccountMapperImpl;
import com.devsu.domain.entity.Account;
import com.devsu.domain.exception.UseCaseException;
import com.devsu.domain.usecase.CreateAccountUseCase;
import com.devsu.domain.usecase.DeleteAccountUseCase;
import com.devsu.domain.usecase.FindAccountUseCase;
import com.devsu.domain.usecase.GetAllAccountsUseCase;
import com.devsu.domain.usecase.UpdateAccountUseCase;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

  @InjectMocks
  private AccountController controller;

  @Mock
  private CreateAccountUseCase createAccountUseCase;

  @Mock
  private GetAllAccountsUseCase getAllAccountsUseCase;

  @Mock
  private FindAccountUseCase findAccountUseCase;

  @Mock
  private UpdateAccountUseCase updateAccountUseCase;

  @Mock
  private DeleteAccountUseCase deleteAccountUseCase;

  @Spy
  private AccountMapperImpl accountMapper;

  @Nested
  class GetAllAccounts {

    @Test
    void given_useCaseWorks_when_getAllAccounts_then_returnExpectedAccounts() {
      when(AccountControllerTest.this.getAllAccountsUseCase.handle()).thenReturn(List.of(AccountMother.complete()));

      final var result = AccountControllerTest.this.controller.getAllAccounts();

      assertNotNull(result);
      assertEquals(HttpStatus.OK, result.getStatusCode());
      assertNotNull(result.getBody());
      assertEquals(1, result.getBody().size());
      verify(AccountControllerTest.this.getAllAccountsUseCase).handle();
      verify(AccountControllerTest.this.accountMapper).fromDomain(anyList());

    }

    @Test
    void given_useCaseFails_when_getAllAccounts_then_throwUseCaseException() {
      when(AccountControllerTest.this.getAllAccountsUseCase.handle()).thenThrow(UseCaseException.class);

      assertThrows(UseCaseException.class, () -> AccountControllerTest.this.controller.getAllAccounts());

      verify(AccountControllerTest.this.getAllAccountsUseCase).handle();
    }
  }

  @Nested
  class CreateAccount {

    @Test
    void given_useCaseWorks_when_createAccount_then_returnExpectedMessage() {
      final var result = AccountControllerTest.this.controller.createAccount(AccountRequestDtoMother.complete());

      assertNotNull(result);
      assertEquals(HttpStatus.OK, result.getStatusCode());
      assertNotNull(result.getBody());
      assertEquals("Cuenta creada satisfactoriamente", result.getBody().getMessage());
      verify(AccountControllerTest.this.createAccountUseCase).handle(any());
    }

    @Test
    void given_useCaseFails_when_createAccount_then_throwUseCaseException() {
      doThrow(UseCaseException.class).when(AccountControllerTest.this.createAccountUseCase).handle(any());

      assertThrows(UseCaseException.class,
          () -> AccountControllerTest.this.controller.createAccount(AccountRequestDtoMother.complete()));

      verify(AccountControllerTest.this.createAccountUseCase).handle(any());
    }
  }

  @Nested
  class FindAccount {

    @Test
    void given_useCaseWorks_when_findAccount_then_returnExpectedAccount() {
      when(AccountControllerTest.this.findAccountUseCase.handle(AccountMother.ACCOUNT_ID)).thenReturn(AccountMother.complete());

      final var result = AccountControllerTest.this.controller.getAccount(AccountMother.ACCOUNT_ID);

      assertNotNull(result);
      assertEquals(HttpStatus.OK, result.getStatusCode());
      assertNotNull(result.getBody());
      verify(AccountControllerTest.this.findAccountUseCase).handle(AccountMother.ACCOUNT_ID);
      verify(AccountControllerTest.this.accountMapper).fromDomain(any(Account.class));
    }

    @Test
    void given_useCaseFails_when_findAccount_then_throwUseCaseException() {
      when(AccountControllerTest.this.findAccountUseCase.handle(AccountMother.ACCOUNT_ID)).thenThrow(UseCaseException.class);

      assertThrows(UseCaseException.class, () -> AccountControllerTest.this.controller.getAccount(AccountMother.ACCOUNT_ID));

      verify(AccountControllerTest.this.findAccountUseCase).handle(AccountMother.ACCOUNT_ID);
    }

  }

  @Nested
  class UpdateAccount {

    @Test
    void given_useCaseWorks_when_updateAccount_then_returnExpectedMessage() {
      final var result =
          AccountControllerTest.this.controller.updateAccount(AccountRequestDtoMother.complete(), AccountMother.ACCOUNT_ID);

      assertNotNull(result);
      assertEquals(HttpStatus.OK, result.getStatusCode());
      assertNotNull(result.getBody());
      assertEquals("Cuenta modificada satisfactoriamente", result.getBody().getMessage());
      verify(AccountControllerTest.this.updateAccountUseCase).handle(any());
    }

    @Test
    void given_useCaseFails_when_updateAccount_then_throwUseCaseException() {
      doThrow(UseCaseException.class).when(AccountControllerTest.this.updateAccountUseCase).handle(any());

      assertThrows(UseCaseException.class,
          () -> AccountControllerTest.this.controller.updateAccount(AccountRequestDtoMother.complete(), AccountMother.ACCOUNT_ID));

      verify(AccountControllerTest.this.updateAccountUseCase).handle(any());
    }
  }

  @Nested
  class DeleteAccount {

    @Test
    void given_useCaseWorks_when_deleteAccount_then_returnExpectedMessage() {
      final var result =
          AccountControllerTest.this.controller.deleteAccount(AccountMother.ACCOUNT_ID);

      assertNotNull(result);
      assertEquals(HttpStatus.OK, result.getStatusCode());
      assertNotNull(result.getBody());
      assertEquals("Cuenta eliminada satisfactoriamente", result.getBody().getMessage());
      verify(AccountControllerTest.this.deleteAccountUseCase).handle(AccountMother.ACCOUNT_ID);
    }

    @Test
    void given_useCaseFails_when_deleteAccount_then_throwUseCaseException() {
      doThrow(UseCaseException.class).when(AccountControllerTest.this.deleteAccountUseCase).handle(any());

      assertThrows(UseCaseException.class, () -> AccountControllerTest.this.controller.deleteAccount(AccountMother.ACCOUNT_ID));

      verify(AccountControllerTest.this.deleteAccountUseCase).handle(AccountMother.ACCOUNT_ID);

    }
  }


}