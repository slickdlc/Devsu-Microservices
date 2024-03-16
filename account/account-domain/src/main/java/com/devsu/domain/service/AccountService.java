package com.devsu.domain.service;

import com.devsu.domain.entity.Account;

import java.util.List;

public interface AccountService {

    void createAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(Integer accountId);

    List<Account> getAllAccounts();

    Account getAccountById(Integer accountId);
}
