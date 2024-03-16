package com.devsu.account.application.service;

import java.util.List;

import com.devsu.domain.entity.Account;
import com.devsu.domain.exception.ServiceException;
import com.devsu.domain.repository.AccountRepository;
import com.devsu.domain.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  @Override
  public void createAccount(final Account account) {
    if (account.getAccountId() != null) {
      throw new ServiceException(HttpStatus.BAD_REQUEST, "Account Id must be null");
    }
    this.saveAccount(account);
  }

  @Override
  public void updateAccount(final Account account) {
    this.accountRepository.findAccountById(account.getAccountId())
        .ifPresentOrElse(
            accountFound -> this.saveAccount(account),
            () -> {
              throw new ServiceException(HttpStatus.NOT_FOUND,
                  String.format("Account with id [%s] not found", account.getAccountId()));
            });
  }

  @Override
  public void deleteAccount(final Integer accountId) {
    this.accountRepository.findAccountById(accountId)
        .ifPresentOrElse(
            account -> this.accountRepository.deleteAccountById(accountId),
            () -> {
              throw new ServiceException(HttpStatus.NOT_FOUND, String.format("Account with id [%s] not found", accountId));
            });
  }

  @Override
  public List<Account> getAllAccounts() {
    return this.accountRepository.readAccounts();
  }

  @Override
  public Account getAccountById(final Integer accountId) {
    return this.accountRepository.findAccountById(accountId)
        .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, String.format("Account with id [%s] not found", accountId)));
  }

  private void saveAccount(final Account account) {
    this.validateToSave(account);
    this.accountRepository.saveAccount(account);
  }

  private void validateToSave(final Account account) {
    this.accountRepository.findByAccountNumber(account.getAccountNumber())
        .filter(accountFound -> !accountFound.getAccountId().equals(account.getAccountId()))
        .ifPresent(c -> {
          throw new ServiceException(HttpStatus.BAD_REQUEST,
              String.format("Account with accountNumber [%s] already exists", c.getAccountNumber()));
        });
  }

}
