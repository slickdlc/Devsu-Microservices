package com.devsu.customer.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.devsu.CustomerMother;
import com.devsu.customer.infrastructure.entity.CustomerEntityMother;
import com.devsu.customer.infrastructure.mapper.CustomerEntityMapperImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.orm.jpa.JpaSystemException;

@ExtendWith(MockitoExtension.class)
class CustomerRepositoryImplTest {

  @InjectMocks
  private CustomerRepositoryImpl customerRepository;

  @Mock
  private CustomerJpaRepository customerJpaRepository;

  @Spy
  private CustomerEntityMapperImpl customerMapper;

  @Nested
  class SaveCustomer {

    @Test
    void given_negativeCase_when_saveCustomer_then_throwsException() {
      when(CustomerRepositoryImplTest.this.customerJpaRepository.save(any())).thenThrow(JpaSystemException.class);

      assertThrows(JpaSystemException.class,
          () -> CustomerRepositoryImplTest.this.customerRepository.saveCustomer(CustomerMother.complete()));
    }

    @Test
    void given_positiveCase_when_saveCustomer_then_returnCustomer() {
      CustomerRepositoryImplTest.this.customerRepository.saveCustomer(CustomerMother.complete());

      verify(CustomerRepositoryImplTest.this.customerJpaRepository).save(any());
    }
  }

  @Nested
  class DeleteCustomerById {

    @Test
    void given_negativeCase_when_deleteCustomerById_then_throwsException() {
      doThrow(JpaSystemException.class).when(CustomerRepositoryImplTest.this.customerJpaRepository).deleteById(any());

      assertThrows(JpaSystemException.class,
          () -> CustomerRepositoryImplTest.this.customerRepository.deleteCustomerById(CustomerMother.CUSTOMER_ID));

      verify(CustomerRepositoryImplTest.this.customerJpaRepository).deleteById(CustomerMother.CUSTOMER_ID);
    }

    @Test
    void given_positiveCase_when_deleteCustomerById_then_returnCustomer() {
      doNothing().when(CustomerRepositoryImplTest.this.customerJpaRepository).deleteById(any());

      CustomerRepositoryImplTest.this.customerRepository.deleteCustomerById(CustomerMother.CUSTOMER_ID);

      verify(CustomerRepositoryImplTest.this.customerJpaRepository).deleteById(CustomerMother.CUSTOMER_ID);
    }
  }

  @Nested
  class ReadCustomers {

    @Test
    void given_negativeCase_when_readCustomers_then_throwsException() {
      when(CustomerRepositoryImplTest.this.customerJpaRepository.findAll()).thenThrow(JpaSystemException.class);

      assertThrows(JpaSystemException.class, () -> CustomerRepositoryImplTest.this.customerRepository.readCustomers());

      verify(CustomerRepositoryImplTest.this.customerJpaRepository).findAll();
    }

    @Test
    void given_positiveCase_when_readCustomers_then_returnCustomers() {
      when(CustomerRepositoryImplTest.this.customerJpaRepository.findAll()).thenReturn(List.of(CustomerEntityMother.complete()));

      CustomerRepositoryImplTest.this.customerRepository.readCustomers();

      verify(CustomerRepositoryImplTest.this.customerJpaRepository).findAll();
    }
  }

  @Nested
  class FindCustomerById {

    @Test
    void given_negativeCase_when_findCustomerById_then_throwsException() {
      when(CustomerRepositoryImplTest.this.customerJpaRepository.findById(CustomerMother.CUSTOMER_ID)).thenThrow(JpaSystemException.class);

      assertThrows(JpaSystemException.class,
          () -> CustomerRepositoryImplTest.this.customerRepository.findCustomerById(CustomerMother.CUSTOMER_ID));

      verify(CustomerRepositoryImplTest.this.customerJpaRepository).findById(CustomerMother.CUSTOMER_ID);
    }

    @Test
    void given_positiveCase_when_findCustomerById_then_returnCustomer() {
      when(CustomerRepositoryImplTest.this.customerJpaRepository.findById(CustomerMother.CUSTOMER_ID)).thenReturn(
          java.util.Optional.of(CustomerEntityMother.complete()));

      CustomerRepositoryImplTest.this.customerRepository.findCustomerById(CustomerMother.CUSTOMER_ID);

      verify(CustomerRepositoryImplTest.this.customerJpaRepository).findById(CustomerMother.CUSTOMER_ID);
    }
  }

  @Nested
  class FindCustomerByIdentification {

    @Test
    void given_negativeCase_when_findCustomerByIdentification_then_throwsException() {
      when(CustomerRepositoryImplTest.this.customerJpaRepository.findByIdentification(CustomerMother.IDENTIFICATION)).thenThrow(
          JpaSystemException.class);

      assertThrows(JpaSystemException.class,
          () -> CustomerRepositoryImplTest.this.customerRepository.findCustomerByIdentification(CustomerMother.IDENTIFICATION));

      verify(CustomerRepositoryImplTest.this.customerJpaRepository).findByIdentification(CustomerMother.IDENTIFICATION);
    }

    @Test
    void given_positiveCase_when_findCustomerByIdentification_then_returnCustomer() {
      when(CustomerRepositoryImplTest.this.customerJpaRepository.findByIdentification(CustomerMother.IDENTIFICATION)).thenReturn(
          java.util.Optional.of(CustomerEntityMother.complete()));

      CustomerRepositoryImplTest.this.customerRepository.findCustomerByIdentification(CustomerMother.IDENTIFICATION);

      verify(CustomerRepositoryImplTest.this.customerJpaRepository).findByIdentification(CustomerMother.IDENTIFICATION);
    }
  }

}