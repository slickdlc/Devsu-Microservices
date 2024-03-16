package com.devsu.customer.application.usecase;

import com.devsu.customer.domain.exception.ServiceException;
import com.devsu.customer.domain.exception.UseCaseException;
import com.devsu.customer.domain.service.CustomerService;
import com.devsu.customer.domain.usecase.DeleteCustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteCustomerUseCaseImpl implements DeleteCustomerUseCase {

  private final CustomerService customerService;

  @Override
  public void handle(final Integer id) {
    try {
      this.customerService.deleteCustomer(id);
    } catch (ServiceException e) {
      throw new UseCaseException(e);
    }
  }
}
