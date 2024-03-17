package com.devsu.customer.application.usecase;

import com.devsu.customer.domain.entity.Customer;
import com.devsu.customer.domain.exception.ServiceException;
import com.devsu.customer.domain.exception.UseCaseException;
import com.devsu.customer.domain.service.CustomerService;
import com.devsu.customer.domain.usecase.PatchCustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PatchCustomerUseCaseImpl implements PatchCustomerUseCase {

  private final CustomerService customerService;

  @Override
  public void handle(final Customer customer) {
    try {
      this.customerService.patchCustomer(customer);
    } catch (ServiceException e) {
      throw new UseCaseException(e);
    }
  }
}
