package com.devsu.account.application.usecase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.devsu.entity.AccountMother;
import com.devsu.domain.exception.ServiceException;
import com.devsu.domain.exception.UseCaseException;
import com.devsu.domain.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllAccountsUseCaseImplTest {

  @InjectMocks
  private GetAllAccountsUseCaseImpl useCase;

  @Mock
  private AccountService accountService;

  @Test
  void given_serviceWorks_when_handle_then_successUseCase() {
    when(this.accountService.getAllAccounts()).thenReturn(List.of(AccountMother.complete()));

    assertDoesNotThrow(() -> this.useCase.handle());

    verify(this.accountService).getAllAccounts();
  }

  @Test
  void given_serviceFails_when_handle_then_throwUseCaseException() {
    doThrow(ServiceException.class).when(this.accountService).getAllAccounts();

    assertThrows(UseCaseException.class, () -> this.useCase.handle());

    verify(this.accountService).getAllAccounts();
  }
}