package com.devsu.account.application.usecase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

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
class UpdateAccountUseCaseImplTest {

  @InjectMocks
  private UpdateAccountUseCaseImpl useCase;

  @Mock
  private AccountService accountService;

  @Test
  void given_serviceWorks_when_handle_then_successUseCase() {
    assertDoesNotThrow(() -> this.useCase.handle(AccountMother.complete()));

    verify(this.accountService).updateAccount(AccountMother.complete());
  }

  @Test
  void given_serviceFails_when_handle_then_throwUseCaseException() {
    doThrow(ServiceException.class).when(this.accountService).updateAccount(AccountMother.complete());

    assertThrows(UseCaseException.class, () -> this.useCase.handle(AccountMother.complete()));

    verify(this.accountService).updateAccount(AccountMother.complete());
  }
}