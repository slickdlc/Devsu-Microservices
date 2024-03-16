package com.devsu.account.application.usecase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import com.devsu.domain.exception.ServiceException;
import com.devsu.domain.exception.UseCaseException;
import com.devsu.domain.service.MovementService;
import com.devsu.entity.MovementMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AddMovementUseCaseImplTest {

  @InjectMocks
  private AddMovementUseCaseImpl useCase;

  @Mock
  private MovementService movementService;

  @Test
  void given_serviceWorks_when_handle_then_successUseCase() {
    assertDoesNotThrow(() -> this.useCase.handle(MovementMother.complete()));

    verify(this.movementService).addMovement(MovementMother.complete());
  }

  @Test
  void given_serviceFails_when_handle_then_throwUseCaseException() {
    doThrow(ServiceException.class).when(this.movementService).addMovement(MovementMother.complete());

    assertThrows(UseCaseException.class, () -> this.useCase.handle(MovementMother.complete()));

    verify(this.movementService).addMovement(MovementMother.complete());
  }
}