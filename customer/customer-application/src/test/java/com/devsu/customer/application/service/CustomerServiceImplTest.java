package com.devsu.customer.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.devsu.CustomerMother;
import com.devsu.customer.domain.exception.ServiceException;
import com.devsu.customer.domain.repository.CustomerRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

  @InjectMocks
  private CustomerServiceImpl customerService;

  @Mock
  private CustomerRepository customerRepository;

  @Nested
  class CreateCustomer {

    @Test
    void given_customerWithId_when_createCustomer_then_expectedException() {
      final var customer = CustomerMother.complete();

      assertThrows(ServiceException.class, () -> CustomerServiceImplTest.this.customerService.createCustomer(customer));

      verify(CustomerServiceImplTest.this.customerRepository, never()).findCustomerByIdentification(customer.getIdentification());
      verify(CustomerServiceImplTest.this.customerRepository, never()).saveCustomer(customer);
    }

    @Test
    void given_customerWithIdentificationThatExists_when_createCustomer_then_expectedException() {
      final var customer = CustomerMother.withCustomerId(null);

      when(CustomerServiceImplTest.this.customerRepository.findCustomerByIdentification(customer.getIdentification())).thenReturn(
          Optional.of(CustomerMother.complete()));

      assertThrows(ServiceException.class, () -> CustomerServiceImplTest.this.customerService.createCustomer(customer));

      verify(CustomerServiceImplTest.this.customerRepository).findCustomerByIdentification(customer.getIdentification());
      verify(CustomerServiceImplTest.this.customerRepository, never()).saveCustomer(customer);
    }

    @Test
    void given_positiveCase_when_createCustomer_then_customerIsCreated() {
      final var customer = CustomerMother.withCustomerId(null);

      when(CustomerServiceImplTest.this.customerRepository.findCustomerByIdentification(customer.getIdentification())).thenReturn(
          Optional.empty());

      CustomerServiceImplTest.this.customerService.createCustomer(customer);

      verify(CustomerServiceImplTest.this.customerRepository).findCustomerByIdentification(customer.getIdentification());
      verify(CustomerServiceImplTest.this.customerRepository).saveCustomer(customer);
    }
  }

  @Nested
  class UpdateCustomer {

    @Test
    void given_customerWithIdThatDoesNotExists_when_updateCustomer_then_expectedException() {
      final var customer = CustomerMother.withCustomerId(9999);

      when(CustomerServiceImplTest.this.customerRepository.findCustomerById(customer.getCustomerId())).thenReturn(Optional.empty());

      assertThrows(ServiceException.class, () -> CustomerServiceImplTest.this.customerService.updateCustomer(customer));

      verify(CustomerServiceImplTest.this.customerRepository).findCustomerById(customer.getCustomerId());
      verify(CustomerServiceImplTest.this.customerRepository, never()).findCustomerByIdentification(customer.getIdentification());
      verify(CustomerServiceImplTest.this.customerRepository, never()).saveCustomer(customer);
    }

    @Test
    void given_customerWithIdentificationThatExists_when_updateCustomer_then_expectedException() {
      final var customer = CustomerMother.withCustomerId(null);

      when(CustomerServiceImplTest.this.customerRepository.findCustomerById(customer.getCustomerId())).thenReturn(
          Optional.of(CustomerMother.complete()));
      when(CustomerServiceImplTest.this.customerRepository.findCustomerByIdentification(customer.getIdentification())).thenReturn(
          Optional.of(CustomerMother.complete()));

      assertThrows(ServiceException.class, () -> CustomerServiceImplTest.this.customerService.updateCustomer(customer));

      verify(CustomerServiceImplTest.this.customerRepository).findCustomerById(customer.getCustomerId());
      verify(CustomerServiceImplTest.this.customerRepository).findCustomerByIdentification(customer.getIdentification());
      verify(CustomerServiceImplTest.this.customerRepository, never()).saveCustomer(customer);
    }

    @Test
    void given_positiveCase_when_updateCustomer_then_customerIsUpdated() {
      final var customer = CustomerMother.withCustomerId(null);

      when(CustomerServiceImplTest.this.customerRepository.findCustomerById(customer.getCustomerId())).thenReturn(
          Optional.of(CustomerMother.complete()));
      when(CustomerServiceImplTest.this.customerRepository.findCustomerByIdentification(customer.getIdentification())).thenReturn(
          Optional.empty());

      CustomerServiceImplTest.this.customerService.updateCustomer(customer);

      verify(CustomerServiceImplTest.this.customerRepository).findCustomerById(customer.getCustomerId());
      verify(CustomerServiceImplTest.this.customerRepository).findCustomerByIdentification(customer.getIdentification());
      verify(CustomerServiceImplTest.this.customerRepository).saveCustomer(customer);
    }
  }

  @Nested
  class DeleteCustomer {

    @Test
    void given_customerIdThatNotExists_when_deleteCustomer_then_expectedException() {
      when(CustomerServiceImplTest.this.customerRepository.findCustomerById(CustomerMother.CUSTOMER_ID)).thenReturn(Optional.empty());

      assertThrows(ServiceException.class, () -> CustomerServiceImplTest.this.customerService.deleteCustomer(CustomerMother.CUSTOMER_ID));

      verify(CustomerServiceImplTest.this.customerRepository).findCustomerById(CustomerMother.CUSTOMER_ID);
      verify(CustomerServiceImplTest.this.customerRepository, never()).deleteCustomerById(CustomerMother.CUSTOMER_ID);
    }

    @Test
    void given_positiveCase_when_deleteCustomer_then_customerIsDeleted() {
      when(CustomerServiceImplTest.this.customerRepository.findCustomerById(CustomerMother.CUSTOMER_ID)).thenReturn(
          Optional.of(CustomerMother.complete()));

      CustomerServiceImplTest.this.customerService.deleteCustomer(CustomerMother.CUSTOMER_ID);

      verify(CustomerServiceImplTest.this.customerRepository).findCustomerById(CustomerMother.CUSTOMER_ID);
      verify(CustomerServiceImplTest.this.customerRepository).deleteCustomerById(CustomerMother.CUSTOMER_ID);
    }
  }

  @Nested
  class GetAllCustomers {

    @Test
    void given_positiveCase_when_getAllCustomers_then_expectedCustomers() {
      when(CustomerServiceImplTest.this.customerRepository.readCustomers()).thenReturn(List.of(CustomerMother.complete()));

      final var result = CustomerServiceImplTest.this.customerService.getAllCustomers();

      assertNotNull(result);
      assertEquals(1, result.size());
      assertEquals(CustomerMother.complete(), result.get(0));
      verify(CustomerServiceImplTest.this.customerRepository).readCustomers();
    }
  }

  @Nested
  class GetCustomerById {

    @Test
    void given_customerIdThatNotExists_when_getCustomerById_then_expectedException() {
      when(CustomerServiceImplTest.this.customerRepository.findCustomerById(CustomerMother.CUSTOMER_ID)).thenReturn(Optional.empty());

      assertThrows(ServiceException.class, () -> CustomerServiceImplTest.this.customerService.getCustomerById(CustomerMother.CUSTOMER_ID));

      verify(CustomerServiceImplTest.this.customerRepository).findCustomerById(CustomerMother.CUSTOMER_ID);
    }

    @Test
    void given_positiveCase_when_getCustomerById_then_expectedCustomer() {
      when(CustomerServiceImplTest.this.customerRepository.findCustomerById(CustomerMother.CUSTOMER_ID)).thenReturn(
          Optional.of(CustomerMother.complete()));

      final var result = CustomerServiceImplTest.this.customerService.getCustomerById(CustomerMother.CUSTOMER_ID);

      assertNotNull(result);
      assertEquals(CustomerMother.complete(), result);
      verify(CustomerServiceImplTest.this.customerRepository).findCustomerById(CustomerMother.CUSTOMER_ID);
    }
  }
}