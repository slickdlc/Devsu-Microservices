package com.devsu.customer.domain.usecase;

import com.devsu.customer.domain.entity.Customer;

public interface CreateCustomerUseCase {

  void handle(Customer customer);
}
