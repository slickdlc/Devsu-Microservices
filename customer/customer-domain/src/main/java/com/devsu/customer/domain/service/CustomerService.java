package com.devsu.customer.domain.service;

import com.devsu.customer.domain.entity.Customer;

import java.util.List;

public interface CustomerService {

    void createCustomer(final Customer customer);

    void updateCustomer(final Customer customer);

    void deleteCustomer(final Integer customerId);

    List<Customer> getAllCustomers();

    Customer getCustomerById(final Integer customerId);

    Customer getCustomerByIdentification(final String identification);
}
