package com.devsu.domain.service;

import com.devsu.domain.entity.Customer;

import java.util.List;

public interface CustomerService {

    void createCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(Integer customerId);

    List<Customer> getAllCustomers();

    Customer getCustomerById(Integer customerId);
}
