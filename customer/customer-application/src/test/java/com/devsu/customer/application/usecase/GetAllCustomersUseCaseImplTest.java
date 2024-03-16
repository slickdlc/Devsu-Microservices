package com.devsu.customer.application.usecase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.devsu.CustomerMother;
import com.devsu.domain.exception.ServiceException;
import com.devsu.domain.exception.UseCaseException;
import com.devsu.domain.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllCustomersUseCaseImplTest {

  @InjectMocks
  private GetAllCustomersUseCaseImpl useCase;

  @Mock
  private CustomerService customerService;

  @Test
  void given_serviceWorks_when_handle_then_successUseCase() {
    when(this.customerService.getAllCustomers()).thenReturn(List.of(CustomerMother.complete()));

    assertDoesNotThrow(() -> this.useCase.handle());

    verify(this.customerService).getAllCustomers();
  }

  @Test
  void given_serviceFails_when_handle_then_throwUseCaseException() {
    doThrow(ServiceException.class).when(this.customerService).getAllCustomers();

    assertThrows(UseCaseException.class, () -> this.useCase.handle());

    verify(this.customerService).getAllCustomers();
  }
}