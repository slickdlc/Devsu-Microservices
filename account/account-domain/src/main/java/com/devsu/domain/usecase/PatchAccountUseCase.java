package com.devsu.domain.usecase;

import com.devsu.domain.entity.Account;

public interface PatchAccountUseCase {

  void handle(final Account account);
}
