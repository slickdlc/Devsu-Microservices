package com.devsu.account.application.usecase;

import com.devsu.domain.exception.ServiceException;
import com.devsu.domain.exception.UseCaseException;
import com.devsu.domain.service.AccountService;
import com.devsu.domain.usecase.DeleteAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteAccountUseCaseImpl implements DeleteAccountUseCase {

  private final AccountService accountService;

  @Override
  public void handle(final Integer id) {
    try {
      this.accountService.deleteAccount(id);
    } catch (ServiceException e) {
      throw new UseCaseException(e);
    }
  }
}
