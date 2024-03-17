package com.devsu.customer.apirest.customer.apirest.customer.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.devsu.CustomerMother;
import com.devsu.customer.apirest.customer.controller.CustomerController;
import com.devsu.customer.apirest.customer.apirest.customer.dto.CustomerRequestDtoMother;
import com.devsu.customer.apirest.customer.mapper.CustomerMapperImpl;
import com.devsu.customer.domain.entity.Customer;
import com.devsu.customer.domain.exception.UseCaseException;
import com.devsu.customer.domain.usecase.CreateCustomerUseCase;
import com.devsu.customer.domain.usecase.DeleteCustomerUseCase;
import com.devsu.customer.domain.usecase.FindCustomerUseCase;
import com.devsu.customer.domain.usecase.GetAllCustomersUseCase;
import com.devsu.customer.domain.usecase.UpdateCustomerUseCase;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

  @InjectMocks
  private CustomerController controller;

  @Mock
  private CreateCustomerUseCase createCustomerUseCase;

  @Mock
  private GetAllCustomersUseCase getAllCustomersUseCase;

  @Mock
  private FindCustomerUseCase findCustomerUseCase;

  @Mock
  private UpdateCustomerUseCase updateCustomerUseCase;

  @Mock
  private DeleteCustomerUseCase deleteCustomerUseCase;

  @Spy
  private CustomerMapperImpl customerMapper;

  @Nested
  class GetAllCustomers {

    @Test
    void given_useCaseWorks_when_getAllCustomers_then_returnExpectedCustomers() {
      when(CustomerControllerTest.this.getAllCustomersUseCase.handle()).thenReturn(List.of(CustomerMother.complete()));

      final var result = CustomerControllerTest.this.controller.getAllCustomers();

      assertNotNull(result);
      assertEquals(HttpStatus.OK, result.getStatusCode());
      assertNotNull(result.getBody());
      assertEquals(1, result.getBody().size());
      verify(CustomerControllerTest.this.getAllCustomersUseCase).handle();
      verify(CustomerControllerTest.this.customerMapper).fromDomain(anyList());

    }

    @Test
    void given_useCaseFails_when_getAllCustomers_then_throwUseCaseException() {
      when(CustomerControllerTest.this.getAllCustomersUseCase.handle()).thenThrow(UseCaseException.class);

      assertThrows(UseCaseException.class, () -> CustomerControllerTest.this.controller.getAllCustomers());

      verify(CustomerControllerTest.this.getAllCustomersUseCase).handle();
    }
  }

  @Nested
  class CreateCustomer {

    @Test
    void given_useCaseWorks_when_createCustomer_then_returnExpectedMessage() {
      final var result = CustomerControllerTest.this.controller.createCustomer(CustomerRequestDtoMother.complete());

      assertNotNull(result);
      assertEquals(HttpStatus.OK, result.getStatusCode());
      assertNotNull(result.getBody());
      assertEquals("El cliente ha sido creado satisfactoriamente", result.getBody().getMessage());
      verify(CustomerControllerTest.this.createCustomerUseCase).handle(any());
    }

    @Test
    void given_useCaseFails_when_createCustomer_then_throwUseCaseException() {
      doThrow(UseCaseException.class).when(CustomerControllerTest.this.createCustomerUseCase).handle(any());

      assertThrows(UseCaseException.class,
          () -> CustomerControllerTest.this.controller.createCustomer(CustomerRequestDtoMother.complete()));

      verify(CustomerControllerTest.this.createCustomerUseCase).handle(any());
    }
  }

  @Nested
  class FindCustomer {

    @Test
    void given_useCaseWorks_when_findCustomer_then_returnExpectedCustomer() {
      when(CustomerControllerTest.this.findCustomerUseCase.handle(CustomerMother.CUSTOMER_ID)).thenReturn(CustomerMother.complete());

      final var result = CustomerControllerTest.this.controller.getCustomer(CustomerMother.CUSTOMER_ID);

      assertNotNull(result);
      assertEquals(HttpStatus.OK, result.getStatusCode());
      assertNotNull(result.getBody());
      verify(CustomerControllerTest.this.findCustomerUseCase).handle(CustomerMother.CUSTOMER_ID);
      verify(CustomerControllerTest.this.customerMapper).fromDomain(any(Customer.class));
    }

    @Test
    void given_useCaseFails_when_findCustomer_then_throwUseCaseException() {
      when(CustomerControllerTest.this.findCustomerUseCase.handle(CustomerMother.CUSTOMER_ID)).thenThrow(UseCaseException.class);

      assertThrows(UseCaseException.class, () -> CustomerControllerTest.this.controller.getCustomer(CustomerMother.CUSTOMER_ID));

      verify(CustomerControllerTest.this.findCustomerUseCase).handle(CustomerMother.CUSTOMER_ID);
    }

  }

  @Nested
  class UpdateCustomer {

    @Test
    void given_useCaseWorks_when_updateCustomer_then_returnExpectedMessage() {
      final var result =
          CustomerControllerTest.this.controller.updateCustomer(CustomerRequestDtoMother.complete(), CustomerMother.CUSTOMER_ID);

      assertNotNull(result);
      assertEquals(HttpStatus.OK, result.getStatusCode());
      assertNotNull(result.getBody());
      assertEquals("El cliente ha sido actualizado satisfactoriamente", result.getBody().getMessage());
      verify(CustomerControllerTest.this.updateCustomerUseCase).handle(any());
    }

    @Test
    void given_useCaseFails_when_updateCustomer_then_throwUseCaseException() {
      doThrow(UseCaseException.class).when(CustomerControllerTest.this.updateCustomerUseCase).handle(any());

      assertThrows(UseCaseException.class,
          () -> CustomerControllerTest.this.controller.updateCustomer(CustomerRequestDtoMother.complete(), CustomerMother.CUSTOMER_ID));

      verify(CustomerControllerTest.this.updateCustomerUseCase).handle(any());
    }
  }

  @Nested
  class DeleteCustomer {

    @Test
    void given_useCaseWorks_when_deleteCustomer_then_returnExpectedMessage() {
      final var result =
          CustomerControllerTest.this.controller.deleteCustomer(CustomerMother.CUSTOMER_ID);

      assertNotNull(result);
      assertEquals(HttpStatus.OK, result.getStatusCode());
      assertNotNull(result.getBody());
      assertEquals("El cliente ha sido eliminado satisfactoriamente", result.getBody().getMessage());
      verify(CustomerControllerTest.this.deleteCustomerUseCase).handle(CustomerMother.CUSTOMER_ID);
    }

    @Test
    void given_useCaseFails_when_deleteCustomer_then_throwUseCaseException() {
      doThrow(UseCaseException.class).when(CustomerControllerTest.this.deleteCustomerUseCase).handle(any());

      assertThrows(UseCaseException.class, () -> CustomerControllerTest.this.controller.deleteCustomer(CustomerMother.CUSTOMER_ID));

      verify(CustomerControllerTest.this.deleteCustomerUseCase).handle(CustomerMother.CUSTOMER_ID);

    }
  }


}