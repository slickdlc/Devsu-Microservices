package com.devsu.account.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.devsu.account.infrastructure.dto.CustomerDto;
import com.devsu.account.infrastructure.entity.CustomerDtoMother;
import com.devsu.account.infrastructure.mapper.CustomerDtoMapperImpl;
import com.devsu.entity.CustomerMother;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class CustomerRepositoryImplTest {

  @InjectMocks
  private CustomerRepositoryImpl customerRepository;

  @Spy
  private CustomerDtoMapperImpl customerDtoMapper;

  @Mock
  private RestTemplate customerRestTemplate;

  @Nested
  class FindActiveCustomerByCustomerIdentification {

    @Test
    void given_customerIdentificationThatDoesNotExist_when_findActiveCustomer_then_returnEmpty() {
      when(CustomerRepositoryImplTest.this.customerRestTemplate.getForEntity("/customer/identification/12345678", CustomerDto.class))
          .thenThrow(NotFound.class);

      final var result = customerRepository.findActiveCustomer(CustomerMother.CUSTOMER_IDENTIFICATION);

      assertNotNull(result);
      assertTrue(result.isEmpty());
    }

    @Test
    void given_customerIdentificationInactive_when_findActiveCustomer_then_returnEmpty() {
      when(CustomerRepositoryImplTest.this.customerRestTemplate.getForEntity(any(), any()))
          .thenReturn(ResponseEntity.ok(CustomerDtoMother.withActive(false)));

      final var result = customerRepository.findActiveCustomer(CustomerMother.CUSTOMER_IDENTIFICATION);

      assertNotNull(result);
      assertTrue(result.isEmpty());
    }

    @Test
    void given_positiveCase_when_findActiveCustomer_then_returnEmpty() {
      when(CustomerRepositoryImplTest.this.customerRestTemplate.getForEntity("/customer/identification/12345678", CustomerDto.class))
          .thenReturn(ResponseEntity.ok(CustomerDtoMother.complete()));

      final var result = customerRepository.findActiveCustomer(CustomerMother.CUSTOMER_IDENTIFICATION);

      assertNotNull(result);
      assertTrue(result.isPresent());
      assertEquals(CustomerMother.CUSTOMER_ID, result.get().getCustomerId());
      assertEquals(CustomerMother.CUSTOMER_NAME, result.get().getCustomerName());
      assertEquals(CustomerMother.CUSTOMER_IDENTIFICATION, result.get().getIdentification());
    }
  }

  @Nested
  class FindActiveCustomerByCustomerId {

    @Test
    void given_customerIdThatDoesNotExist_when_findActiveCustomer_then_returnEmpty() {
      when(CustomerRepositoryImplTest.this.customerRestTemplate.getForEntity("/customer/1", CustomerDto.class))
          .thenThrow(NotFound.class);

      final var result = customerRepository.findActiveCustomer(CustomerMother.CUSTOMER_ID);

      assertNotNull(result);
      assertTrue(result.isEmpty());
    }

    @Test
    void given_customerIdInactive_when_findActiveCustomer_then_returnEmpty() {
      when(CustomerRepositoryImplTest.this.customerRestTemplate.getForEntity(any(), any()))
          .thenReturn(ResponseEntity.ok(CustomerDtoMother.withActive(false)));

      final var result = customerRepository.findActiveCustomer(CustomerMother.CUSTOMER_ID);

      assertNotNull(result);
      assertTrue(result.isEmpty());
    }

    @Test
    void given_positiveCase_when_findActiveCustomer_then_returnEmpty() {
      when(CustomerRepositoryImplTest.this.customerRestTemplate.getForEntity("/customer/1", CustomerDto.class))
          .thenReturn(ResponseEntity.ok(CustomerDtoMother.complete()));

      final var result = customerRepository.findActiveCustomer(CustomerMother.CUSTOMER_ID);

      assertNotNull(result);
      assertTrue(result.isPresent());
      assertEquals(CustomerMother.CUSTOMER_ID, result.get().getCustomerId());
      assertEquals(CustomerMother.CUSTOMER_NAME, result.get().getCustomerName());
      assertEquals(CustomerMother.CUSTOMER_IDENTIFICATION, result.get().getIdentification());
    }
  }

  @Nested
  class ExistsActiveCustomerByCustomerIdentification {

    @Test
    void given_customerIdentificationThatDoesNotExist_when_existsActiveCustomer_then_returnFalse() {
      when(CustomerRepositoryImplTest.this.customerRestTemplate.getForEntity("/customer/identification/12345678", CustomerDto.class))
          .thenThrow(NotFound.class);

      final var result = customerRepository.existsActiveCustomer(CustomerMother.CUSTOMER_IDENTIFICATION);

      assertFalse(result);
    }

    @Test
    void given_customerIdentificationInactive_when_existsActiveCustomer_then_returnFalse() {
      when(CustomerRepositoryImplTest.this.customerRestTemplate.getForEntity(any(), any()))
          .thenReturn(ResponseEntity.ok(CustomerDtoMother.withActive(false)));

      final var result = customerRepository.existsActiveCustomer(CustomerMother.CUSTOMER_IDENTIFICATION);

      assertFalse(result);
    }

    @Test
    void given_positiveCase_when_existsActiveCustomer_then_returnTrue() {
      when(CustomerRepositoryImplTest.this.customerRestTemplate.getForEntity("/customer/identification/12345678", CustomerDto.class))
          .thenReturn(ResponseEntity.ok(CustomerDtoMother.complete()));

      final var result = customerRepository.existsActiveCustomer(CustomerMother.CUSTOMER_IDENTIFICATION);

      assertTrue(result);
    }
  }

  @Nested
  class ExistsActiveCustomerByCustomerId {

    @Test
    void given_customerIdThatDoesNotExist_when_existsActiveCustomer_then_returnFalse() {
      when(CustomerRepositoryImplTest.this.customerRestTemplate.getForEntity("/customer/1", CustomerDto.class))
          .thenThrow(NotFound.class);

      final var result = customerRepository.existsActiveCustomer(CustomerMother.CUSTOMER_ID);

      assertFalse(result);
    }

    @Test
    void given_customerIdInactive_when_existsActiveCustomer_then_returnFalse() {
      when(CustomerRepositoryImplTest.this.customerRestTemplate.getForEntity(any(), any()))
          .thenReturn(ResponseEntity.ok(CustomerDtoMother.withActive(false)));

      final var result = customerRepository.existsActiveCustomer(CustomerMother.CUSTOMER_ID);

      assertFalse(result);
    }

    @Test
    void given_positiveCase_when_existsActiveCustomer_then_returnTrue() {
      when(CustomerRepositoryImplTest.this.customerRestTemplate.getForEntity("/customer/1", CustomerDto.class))
          .thenReturn(ResponseEntity.ok(CustomerDtoMother.complete()));

      final var result = customerRepository.existsActiveCustomer(CustomerMother.CUSTOMER_ID);

      assertTrue(result);
    }
  }
}
