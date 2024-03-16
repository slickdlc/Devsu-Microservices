package com.devsu.customer.domain.repository;

import java.util.List;
import java.util.Optional;

import com.devsu.customer.domain.entity.Customer;

public interface CustomerRepository {

  void saveCustomer(Customer customer);

  void deleteCustomerById(Integer customerId);

  List<Customer> readCustomers();

  Optional<Customer> findCustomerById(Integer customerId);

  Optional<Customer> findCustomerByIdentification(String identification);
}