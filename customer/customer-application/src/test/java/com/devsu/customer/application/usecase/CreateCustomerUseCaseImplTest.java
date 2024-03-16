package com.devsu.customer.application.usecase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import com.devsu.CustomerMother;
import com.devsu.customer.domain.exception.ServiceException;
import com.devsu.customer.domain.exception.UseCaseException;
import com.devsu.customer.domain.service.CustomerService;
import com.devsu.customer.application.usecase.CreateCustomerUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateCustomerUseCaseImplTest {

  @InjectMocks
  private CreateCustomerUseCaseImpl useCase;

  @Mock
  private CustomerService customerService;

  @Test
  void given_serviceWorks_when_handle_then_successUseCase() {
    assertDoesNotThrow(() -> this.useCase.handle(CustomerMother.complete()));

    verify(this.customerService).createCustomer(CustomerMother.complete());
  }

  @Test
  void given_serviceFails_when_handle_then_throwUseCaseException() {
    doThrow(ServiceException.class).when(this.customerService).createCustomer(CustomerMother.complete());

    assertThrows(UseCaseException.class, () -> this.useCase.handle(CustomerMother.complete()));

    verify(this.customerService).createCustomer(CustomerMother.complete());
  }
}