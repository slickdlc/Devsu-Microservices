package com.devsu.customer.domain.repository;

import java.util.List;
import java.util.Optional;

import com.devsu.customer.domain.entity.Customer;

public interface CustomerRepository {

  void saveCustomer(final Customer customer);

  void deleteCustomerById(final Integer customerId);

  List<Customer> readCustomers();

  Optional<Customer> findCustomerById(final Integer customerId);

  Optional<Customer> findCustomerByIdentification(final String identification);
}
