package com.devsu.domain.usecase;

import java.util.List;

import com.devsu.domain.entity.Account;

public interface GetAllAccountsUseCase {

  List<Account> handle();
}
