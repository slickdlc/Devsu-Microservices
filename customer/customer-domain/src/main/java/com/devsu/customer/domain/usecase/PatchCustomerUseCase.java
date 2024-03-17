package com.devsu.customer.domain.usecase;

import com.devsu.customer.domain.entity.Customer;

public interface PatchCustomerUseCase {

  void handle(final Customer customer);
}
