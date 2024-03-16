package com.devsu.account.application.usecase;

import com.devsu.domain.entity.Account;
import com.devsu.domain.exception.ServiceException;
import com.devsu.domain.exception.UseCaseException;
import com.devsu.domain.service.AccountService;
import com.devsu.domain.usecase.UpdateAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateAccountUseCaseImpl implements UpdateAccountUseCase {

  private final AccountService accountService;

  @Override
  public void handle(final Account account) {
    try {
      this.accountService.updateAccount(account);
    } catch (ServiceException e) {
      throw new UseCaseException(e);
    }
  }
}
