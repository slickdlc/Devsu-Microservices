package com.devsu.domain.usecase;

import com.devsu.domain.entity.Account;

public interface CreateAccountUseCase {

  void handle(final Account account);
}
