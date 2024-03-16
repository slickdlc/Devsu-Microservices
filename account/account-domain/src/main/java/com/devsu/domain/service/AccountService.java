package com.devsu.domain.service;

import java.util.List;

import com.devsu.domain.entity.Account;

public interface AccountService {

  void createAccount(final Account account);

  void updateAccount(final Account account);

  void deleteAccount(final Integer accountId);

  List<Account> getAllAccounts();

  Account getAccountById(final Integer accountId);
}
