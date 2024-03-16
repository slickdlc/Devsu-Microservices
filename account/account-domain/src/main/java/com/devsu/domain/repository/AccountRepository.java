package com.devsu.domain.repository;

import java.util.List;
import java.util.Optional;

import com.devsu.domain.entity.Account;

public interface AccountRepository {

  void saveAccount(final Account account);

  void deleteAccountById(final Integer accountId);

  List<Account> readAccounts();

  Optional<Account> findByAccountId(final Integer accountId);

  Optional<Account> findByAccountNumber(final String accountNumber);

  List<Integer> findActiveAccountIds(final Integer customerId);

}
