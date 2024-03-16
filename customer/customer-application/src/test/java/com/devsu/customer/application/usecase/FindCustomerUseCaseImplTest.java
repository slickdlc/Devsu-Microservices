package com.devsu.customer.application.usecase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
class FindCustomerUseCaseImplTest {

  @InjectMocks
  private FindCustomerUseCaseImpl useCase;

  @Mock
  private CustomerService customerService;

  @Test
  void given_serviceWorks_when_handle_then_successUseCase() {
    when(this.customerService.getCustomerById(CustomerMother.CUSTOMER_ID)).thenReturn(CustomerMother.complete());
    
    assertDoesNotThrow(() -> this.useCase.handle(CustomerMother.CUSTOMER_ID));

    verify(this.customerService).getCustomerById(CustomerMother.CUSTOMER_ID);
  }

  @Test
  void given_serviceFails_when_handle_then_throwUseCaseException() {
    doThrow(ServiceException.class).when(this.customerService).getCustomerById(CustomerMother.CUSTOMER_ID);

    assertThrows(UseCaseException.class, () -> this.useCase.handle(CustomerMother.CUSTOMER_ID));

    verify(this.customerService).getCustomerById(CustomerMother.CUSTOMER_ID);
  }
}