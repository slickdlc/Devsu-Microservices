package com.devsu.customer.domain.usecase;

import java.util.List;

import com.devsu.customer.domain.entity.Customer;

public interface GetAllCustomersUseCase {

  List<Customer> handle();
}
