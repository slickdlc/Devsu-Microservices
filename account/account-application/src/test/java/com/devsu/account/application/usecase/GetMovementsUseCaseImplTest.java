package com.devsu.account.application.usecase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.devsu.domain.exception.ServiceException;
import com.devsu.domain.exception.UseCaseException;
import com.devsu.domain.service.MovementService;
import com.devsu.entity.CustomerMovementMother;
import com.devsu.filter.MovementsByCustomerFilterMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetMovementsUseCaseImplTest {

  @InjectMocks
  private GetMovementsUseCaseImpl useCase;

  @Mock
  private MovementService movementService;

  @Test
  void given_serviceWorks_when_handle_then_successUseCase() {
    when(this.movementService.getMovementsByFilter(any())).thenReturn(List.of(CustomerMovementMother.complete()));

    assertDoesNotThrow(() -> this.useCase.handle(MovementsByCustomerFilterMother.complete()));

    verify(this.movementService).getMovementsByFilter(any());
  }

  @Test
  void given_serviceFails_when_handle_then_throwUseCaseException() {
    doThrow(ServiceException.class).when(this.movementService).getMovementsByFilter(any());

    assertThrows(UseCaseException.class, () -> this.useCase.handle(MovementsByCustomerFilterMother.complete()));

    verify(this.movementService).getMovementsByFilter(any());
  }
}