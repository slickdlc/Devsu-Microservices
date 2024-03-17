package com.devsu.customer.application.usecase;

import com.devsu.customer.domain.entity.Customer;
import com.devsu.customer.domain.exception.ServiceException;
import com.devsu.customer.domain.exception.UseCaseException;
import com.devsu.customer.domain.service.CustomerService;
import com.devsu.customer.domain.usecase.FindCustomerUseCase;
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

  @Override
  public Customer handle(final String identification) {
    try {
      return this.customerService.getCustomerByIdentification(identification);
    } catch (ServiceException e) {
      throw new UseCaseException(e);
    }
  }
}
