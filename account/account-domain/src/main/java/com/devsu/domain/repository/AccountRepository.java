package com.devsu.domain.repository;

import java.util.List;
import java.util.Optional;

import com.devsu.domain.entity.Account;

public interface AccountRepository {

  void saveAccount(Account account);

  void deleteAccountById(Integer accountId);

  List<Account> readAccounts();

  Optional<Account> findAccountById(Integer accountId);

  Optional<Account> findByAccountNumber(String accountNumber);
}
