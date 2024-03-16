package com.devsu.customer.application.usecase;

import com.devsu.domain.entity.Customer;
import com.devsu.domain.exception.ServiceException;
import com.devsu.domain.exception.UseCaseException;
import com.devsu.domain.service.CustomerService;
import com.devsu.domain.usecase.UpdateCustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {

  private final CustomerService customerService;

  @Override
  public void handle(final Customer customer) {
    try {
      this.customerService.updateCustomer(customer);
    } catch (ServiceException e) {
      throw new UseCaseException(e);
    }
  }
}
