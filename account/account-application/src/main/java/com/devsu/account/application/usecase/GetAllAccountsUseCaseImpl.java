package com.devsu.account.application.usecase;

import java.util.List;

import com.devsu.domain.entity.Account;
import com.devsu.domain.exception.ServiceException;
import com.devsu.domain.exception.UseCaseException;
import com.devsu.domain.service.AccountService;
import com.devsu.domain.usecase.GetAllAccountsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAllAccountsUseCaseImpl implements GetAllAccountsUseCase {

  private final AccountService accountService;

  @Override
  public List<Account> handle() {
    try {
      return this.accountService.getAllAccounts();
    } catch (ServiceException e) {
      throw new UseCaseException(e);
    }
  }
}
