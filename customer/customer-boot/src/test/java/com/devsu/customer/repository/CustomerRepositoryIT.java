package com.devsu.customer.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.devsu.CustomerMother;
import com.devsu.customer.config.ApplicationAbstractIT;
import com.devsu.domain.entity.Customer;
import com.devsu.domain.repository.CustomerRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerRepositoryIT extends ApplicationAbstractIT {

  private static final Integer CUSTOMER_ID_THAT_EXISTS = 1;

  private static final String ADDRESS = "Av. 28 de Julio #1999";

  private static final Integer AGE = 26;

  private static final String GENDER = "M";

  private static final String IDENTIFICATION_THAT_EXISTS = "71717171";

  private static final String NAME = "Hans De La Cruz Acosta";

  private static final String PASSWORD = "passw0rd";

  private static final String PHONE = "959825887";

  private static final Boolean ACTIVE = true;

  @Autowired
  private CustomerRepository customerRepository;

  private void validateExpectCustomer(final Customer customer) {
    assertEquals(CUSTOMER_ID_THAT_EXISTS, customer.getCustomerId());
    assertEquals(ADDRESS, customer.getAddress());
    assertEquals(AGE, customer.getAge());
    assertEquals(GENDER, customer.getGender());
    assertEquals(IDENTIFICATION_THAT_EXISTS, customer.getIdentification());
    assertEquals(NAME, customer.getName());
    assertEquals(PASSWORD, customer.getPassword());
    assertEquals(PHONE, customer.getPhone());
    assertEquals(ACTIVE, customer.isActive());
  }

  @Nested
  class SaveCustomer {

    private static final String IDENTIFICATION_DOES_NOT_EXIST = "12341234";

    private static final String IDENTIFICATION_DOES_NOT_EXIST_TWO = "12341235";

    private static final Integer CUSTOMER_ID_TO_UPDATE = 3;

    @Test
    void given_customerToCreate_when_saveCustomer_then_customerIsCreated() {
      final Customer customer = CustomerMother.builder().customerId(null).identification(IDENTIFICATION_DOES_NOT_EXIST).build();

      CustomerRepositoryIT.this.customerRepository.saveCustomer(customer);
      final var customerCreated = CustomerRepositoryIT.this.customerRepository.findCustomerByIdentification(IDENTIFICATION_DOES_NOT_EXIST);

      assertNotNull(customerCreated);
      assertTrue(customerCreated.isPresent());
      assertNotNull(customerCreated.get().getCustomerId());
      assertEquals(IDENTIFICATION_DOES_NOT_EXIST, customerCreated.get().getIdentification());
    }

    @Test
    void given_customerToUpdate_when_saveCustomer_then_customerIsUpdated() {
      final Customer customer =
          CustomerMother.builder().customerId(CUSTOMER_ID_TO_UPDATE).identification(IDENTIFICATION_DOES_NOT_EXIST_TWO).build();

      CustomerRepositoryIT.this.customerRepository.saveCustomer(customer);
      final var customerUpdated = CustomerRepositoryIT.this.customerRepository.findCustomerByIdentification(IDENTIFICATION_DOES_NOT_EXIST_TWO);

      assertNotNull(customerUpdated);
      assertTrue(customerUpdated.isPresent());
      assertEquals(CUSTOMER_ID_TO_UPDATE, customerUpdated.get().getCustomerId());
      assertEquals(IDENTIFICATION_DOES_NOT_EXIST_TWO, customerUpdated.get().getIdentification());
    }
  }

  @Nested
  class DeleteCustomer {

    private static final Integer CUSTOMER_ID_TO_DELETE = 2;

    @Test
    void given_validCustomerId_when_deleteCustomer_then_customerIsDeleted() {
      final var customerBeforeDelete = CustomerRepositoryIT.this.customerRepository.findCustomerById(CUSTOMER_ID_TO_DELETE);

      CustomerRepositoryIT.this.customerRepository.deleteCustomerById(CUSTOMER_ID_TO_DELETE);

      final var customerDeleted = CustomerRepositoryIT.this.customerRepository.findCustomerById(CUSTOMER_ID_TO_DELETE);

      assertNotNull(customerBeforeDelete);
      assertTrue(customerBeforeDelete.isPresent());
      assertNotNull(customerDeleted);
      assertTrue(customerDeleted.isEmpty());
    }
  }

  @Nested
  class ReadCustomers {

    @Test
    void given_nothing_when_readCustomers_then_returnCustomers() {
      final var customers = CustomerRepositoryIT.this.customerRepository.readCustomers();

      assertNotNull(customers);
      assertTrue(customers.size() > 1);
      final var optionalCustomer = customers.stream().filter(c -> CUSTOMER_ID_THAT_EXISTS.equals(c.getCustomerId())).findFirst();
      assertTrue(optionalCustomer.isPresent());
      final var customer = optionalCustomer.get();
      validateExpectCustomer(customer);
    }
  }

  @Nested
  class FindCustomerById {

    @Test
    void given_idThatExists_when_findCustomerById_then_returnCustomers() {
      final var customer = CustomerRepositoryIT.this.customerRepository.findCustomerById(CUSTOMER_ID_THAT_EXISTS);

      assertNotNull(customer);
      assertTrue(customer.isPresent());
      validateExpectCustomer(customer.get());
    }
  }

  @Nested
  class FindCustomerByIdentification {

    @Test
    void given_identificationThatExists_when_findCustomerByIdentification_then_returnCustomers() {
      final var customer = CustomerRepositoryIT.this.customerRepository.findCustomerByIdentification(IDENTIFICATION_THAT_EXISTS);

      assertNotNull(customer);
      assertTrue(customer.isPresent());
      validateExpectCustomer(customer.get());
    }
  }
}