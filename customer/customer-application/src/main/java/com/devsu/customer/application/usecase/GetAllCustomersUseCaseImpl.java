package com.devsu.customer.application.usecase;

import java.util.List;

import com.devsu.customer.domain.entity.Customer;
import com.devsu.customer.domain.exception.ServiceException;
import com.devsu.customer.domain.exception.UseCaseException;
import com.devsu.customer.domain.service.CustomerService;
import com.devsu.customer.domain.usecase.GetAllCustomersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAllCustomersUseCaseImpl implements GetAllCustomersUseCase {

  private final CustomerService customerService;

  @Override
  public List<Customer> handle() {
    try {
      return this.customerService.getAllCustomers();
    } catch (ServiceException e) {
      throw new UseCaseException(e);
    }
  }
}
