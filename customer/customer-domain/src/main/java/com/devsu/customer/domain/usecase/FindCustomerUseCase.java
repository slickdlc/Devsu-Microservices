package com.devsu.customer.domain.usecase;

import com.devsu.customer.domain.entity.Customer;

public interface FindCustomerUseCase {

  Customer handle(final Integer id);

  Customer handle(final String identification);
}
