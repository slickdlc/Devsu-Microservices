package com.devsu.customer.domain.usecase;

import com.devsu.customer.domain.entity.Customer;

public interface FindCustomerUseCase {

  Customer handle(Integer id);
}
