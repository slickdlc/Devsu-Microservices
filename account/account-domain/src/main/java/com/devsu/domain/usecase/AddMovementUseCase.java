package com.devsu.domain.usecase;

import com.devsu.domain.entity.Movement;

public interface AddMovementUseCase {

  void handle(final Movement movement);
}
