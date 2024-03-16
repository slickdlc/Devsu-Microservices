package com.devsu.customer.application.usecase;

import com.devsu.customer.domain.entity.Customer;
import com.devsu.customer.domain.exception.ServiceException;
import com.devsu.customer.domain.exception.UseCaseException;
import com.devsu.customer.domain.service.CustomerService;
import com.devsu.customer.domain.usecase.UpdateCustomerUseCase;
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
