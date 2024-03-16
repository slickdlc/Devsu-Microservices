package com.devsu.domain.repository;

import java.util.Optional;

import com.devsu.domain.entity.Customer;

public interface CustomerRepository {

  Optional<Customer> findActiveCustomer(final String customerIdentification);

  Optional<Customer> findActiveCustomer(final Integer customerId);

  boolean existsActiveCustomer(final String customerIdentification);

  boolean existsActiveCustomer(final Integer customerId);

}
