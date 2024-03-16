package com.devsu.domain.usecase;

import com.devsu.domain.entity.Account;

public interface UpdateAccountUseCase {

  void handle(final Account account);
}
