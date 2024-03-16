package com.devsu.customer.domain.usecase;

import com.devsu.customer.domain.entity.Customer;

public interface UpdateCustomerUseCase {

  void handle(Customer customer);
}
