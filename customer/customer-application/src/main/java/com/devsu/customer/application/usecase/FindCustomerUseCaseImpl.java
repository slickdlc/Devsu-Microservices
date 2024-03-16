package com.devsu.customer.application.usecase;

import com.devsu.domain.entity.Customer;
import com.devsu.domain.exception.ServiceException;
import com.devsu.domain.exception.UseCaseException;
import com.devsu.domain.service.CustomerService;
import com.devsu.domain.usecase.FindCustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindCustomerUseCaseImpl implements FindCustomerUseCase {

  private final CustomerService customerService;

  @Override
  public Customer handle(final Integer id) {
    try {
      return this.customerService.getCustomerById(id);
    } catch (ServiceException e) {
      throw new UseCaseException(e);
    }
  }
}
