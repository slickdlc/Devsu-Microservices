package com.devsu.domain.usecase;

import com.devsu.domain.entity.Customer;

public interface CreateCustomerUseCase {

  void handle(Customer customer);
}
