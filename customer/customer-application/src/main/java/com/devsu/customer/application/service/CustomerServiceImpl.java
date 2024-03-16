package com.devsu.customer.application.service;

import java.util.List;

import com.devsu.domain.entity.Customer;
import com.devsu.domain.exception.ServiceException;
import com.devsu.domain.repository.CustomerRepository;
import com.devsu.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  @Override
  public void createCustomer(final Customer customer) {
    if (customer.getCustomerId() != null) {
      throw new ServiceException(HttpStatus.BAD_REQUEST, "Customer Id must be null");
    }
    this.saveCustomer(customer);
  }

  @Override
  public void updateCustomer(final Customer customer) {
    this.customerRepository.findCustomerById(customer.getCustomerId())
        .ifPresentOrElse(
            customerFound -> this.saveCustomer(customer),
            () -> {
              throw new ServiceException(HttpStatus.NOT_FOUND,
                  String.format("Customer with id [%s] not found", customer.getCustomerId()));
            });
  }

  @Override
  public void deleteCustomer(final Integer customerId) {
    this.customerRepository.findCustomerById(customerId)
        .ifPresentOrElse(
            customer -> this.customerRepository.deleteCustomerById(customerId),
            () -> {
              throw new ServiceException(HttpStatus.NOT_FOUND, String.format("Customer with id [%s] not found", customerId));
            });
  }

  @Override
  public List<Customer> getAllCustomers() {
    return this.customerRepository.readCustomers();
  }

  @Override
  public Customer getCustomerById(final Integer customerId) {
    return this.customerRepository.findCustomerById(customerId)
        .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, String.format("Customer with id [%s] not found", customerId)));
  }

  private void saveCustomer(final Customer customer) {
    this.validateToSave(customer);
    this.customerRepository.saveCustomer(customer);
  }

  private void validateToSave(final Customer customer) {
    this.customerRepository.findCustomerByIdentification(customer.getIdentification())
        .filter(customerFound -> !customerFound.getCustomerId().equals(customer.getCustomerId()))
        .ifPresent(c -> {
          throw new ServiceException(HttpStatus.BAD_REQUEST,
              String.format("Customer with identification [%s] already exists", c.getIdentification()));
        });
  }

}
