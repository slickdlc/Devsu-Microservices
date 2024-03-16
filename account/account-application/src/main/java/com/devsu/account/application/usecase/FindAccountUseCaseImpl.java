package com.devsu.account.application.usecase;

import com.devsu.domain.entity.Account;
import com.devsu.domain.exception.ServiceException;
import com.devsu.domain.exception.UseCaseException;
import com.devsu.domain.service.AccountService;
import com.devsu.domain.usecase.FindAccountUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAccountUseCaseImpl implements FindAccountUseCase {

  private final AccountService accountService;

  @Override
  public Account handle(final Integer id) {
    try {
      return this.accountService.getAccountById(id);
    } catch (ServiceException e) {
      throw new UseCaseException(e);
    }
  }
}
