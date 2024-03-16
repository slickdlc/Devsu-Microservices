package com.devsu.domain.usecase;

import com.devsu.domain.entity.Customer;

public interface FindCustomerUseCase {

  Customer handle(Integer id);
}
