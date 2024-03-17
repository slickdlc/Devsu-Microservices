package com.devsu.customer.domain.service;

import java.util.List;

import com.devsu.customer.domain.entity.Customer;

public interface CustomerService {

  void createCustomer(final Customer customer);

  void updateCustomer(final Customer customer);

  void patchCustomer(final Customer customer);

  void deleteCustomer(final Integer customerId);

  List<Customer> getAllCustomers();

  Customer getCustomerById(final Integer customerId);

  Customer getCustomerByIdentification(final String identification);
}
