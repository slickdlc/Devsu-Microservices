package com.devsu.account.application.usecase;

import com.devsu.domain.entity.Movement;
import com.devsu.domain.exception.ServiceException;
import com.devsu.domain.exception.UseCaseException;
import com.devsu.domain.service.MovementService;
import com.devsu.domain.usecase.AddMovementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddMovementUseCaseImpl implements AddMovementUseCase {

  private final MovementService movementService;

  @Override
  public void handle(final Movement movement) {
    try {
      this.movementService.addMovement(movement);
    } catch (ServiceException e) {
      throw new UseCaseException(e);
    }
  }
}
