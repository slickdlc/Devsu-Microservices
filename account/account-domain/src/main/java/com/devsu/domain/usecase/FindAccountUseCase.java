package com.devsu.domain.usecase;

import com.devsu.domain.entity.Account;

public interface FindAccountUseCase {

  Account handle(final Integer id);
}
