package com.devsu.account.apirest.movement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.devsu.account.apirest.movement.dto.MovementRequestDtoMother;
import com.devsu.account.apirest.movement.mapper.MovementMapperImpl;
import com.devsu.domain.exception.UseCaseException;
import com.devsu.domain.usecase.AddMovementUseCase;
import com.devsu.domain.usecase.GetMovementsUseCase;
import com.devsu.entity.CustomerMother;
import com.devsu.entity.CustomerMovementMother;
import com.devsu.filter.MovementsByCustomerFilterMother;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class MovementControllerTest {

  @InjectMocks
  private MovementController controller;

  @Mock
  private AddMovementUseCase addMovementUseCase;

  @Mock
  private GetMovementsUseCase getMovementsUseCase;

  @Spy
  private MovementMapperImpl movementMapper;

  @Nested
  class GetMovements {

    @Test
    void given_useCaseWorks_when_getMovements_then_returnExpectedMovements() {
      when(MovementControllerTest.this.getMovementsUseCase.handle(any()))
          .thenReturn(List.of(CustomerMovementMother.complete()));

      final var result = MovementControllerTest.this.controller.getAccountStatement(MovementsByCustomerFilterMother.START_DATE,
          MovementsByCustomerFilterMother.END_DATE, null, CustomerMother.CUSTOMER_IDENTIFICATION);

      assertNotNull(result);
      assertEquals(HttpStatus.OK, result.getStatusCode());
      assertNotNull(result.getBody());
      assertEquals(1, result.getBody().size());
      verify(MovementControllerTest.this.getMovementsUseCase).handle(any());
      verify(MovementControllerTest.this.movementMapper).fromDomain(anyList());

    }

    @Test
    void given_useCaseFails_when_getMovements_then_throwUseCaseException() {
      when(MovementControllerTest.this.getMovementsUseCase.handle(any())).thenThrow(UseCaseException.class);

      assertThrows(UseCaseException.class,
          () -> MovementControllerTest.this.controller.getAccountStatement(MovementsByCustomerFilterMother.START_DATE,
              MovementsByCustomerFilterMother.END_DATE, null, CustomerMother.CUSTOMER_IDENTIFICATION));

      verify(MovementControllerTest.this.getMovementsUseCase).handle(any());
    }
  }

  @Nested
  class AddMovement {

    @Test
    void given_useCaseWorks_when_createMovement_then_returnExpectedMessage() {
      final var result = MovementControllerTest.this.controller.addMovement(MovementRequestDtoMother.complete());

      assertNotNull(result);
      assertEquals(HttpStatus.OK, result.getStatusCode());
      assertNotNull(result.getBody());
      assertEquals("Movimiento añadido satisfactoriamente", result.getBody().getMessage());
      verify(MovementControllerTest.this.addMovementUseCase).handle(any());
    }

    @Test
    void given_useCaseFails_when_createMovement_then_throwUseCaseException() {
      doThrow(UseCaseException.class).when(MovementControllerTest.this.addMovementUseCase).handle(any());

      assertThrows(UseCaseException.class,
          () -> MovementControllerTest.this.controller.addMovement(MovementRequestDtoMother.complete()));

      verify(MovementControllerTest.this.addMovementUseCase).handle(any());
    }
  }

}