package com.devsu.domain.usecase;

import java.util.List;

import com.devsu.domain.entity.Customer;

public interface GetAllCustomersUseCase {

  List<Customer> handle();
}
