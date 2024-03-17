package com.devsu.customer.application.service;

import java.util.List;
import java.util.function.Consumer;

import com.devsu.customer.domain.entity.Customer;
import com.devsu.customer.domain.exception.ServiceException;
import com.devsu.customer.domain.repository.CustomerRepository;
import com.devsu.customer.domain.service.CustomerService;
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
      throw new IllegalArgumentException("El id debe ser nulo en la creación de un cliente nuevo");
    }
    this.saveCustomer(customer);
  }

  @Override
  public void updateCustomer(final Customer customer) {
    this.doIfExists(customer.getCustomerId(), customerFound -> this.saveCustomer(customer));
  }

  @Override
  public void patchCustomer(final Customer customer) {
    this.doIfExists(customer.getCustomerId(),
        customerFound ->
        {
          if (customer.getPassword() != null) {
            customerFound.setPassword(customer.getPassword());
          }
          if (customer.getName() != null) {
            customerFound.setName(customer.getName());
          }
          if (customer.getGender() != null) {
            customerFound.setGender(customer.getGender());
          }
          if (customer.getAge() != null) {
            customerFound.setAge(customer.getAge());
          }
          if (customer.getIdentification() != null) {
            customerFound.setIdentification(customer.getIdentification());
          }
          if (customer.getAddress() != null) {
            customerFound.setAddress(customer.getAddress());
          }
          if (customer.getPhone() != null) {
            customerFound.setPhone(customer.getPhone());
          }
          this.saveCustomer(customerFound);
        });
  }

  @Override
  public void deleteCustomer(final Integer customerId) {
    this.doIfExists(customerId, customerFound -> this.customerRepository.deleteCustomerById(customerId));
  }

  public void doIfExists(final Integer customerId, final Consumer<Customer> consumerCustomer) {
    this.customerRepository.findCustomerById(customerId)
        .ifPresentOrElse(
            consumerCustomer,
            () -> {
              throw new ServiceException(HttpStatus.NOT_FOUND, String.format("El cliente con id [%s] no se ha encontrado", customerId));
            });
  }

  @Override
  public List<Customer> getAllCustomers() {
    return this.customerRepository.readCustomers();
  }

  @Override
  public Customer getCustomerById(final Integer customerId) {
    return this.customerRepository.findCustomerById(customerId)
        .orElseThrow(
            () -> new ServiceException(HttpStatus.NOT_FOUND, String.format("El cliente con id [%s] no se ha encontrado", customerId)));
  }

  @Override
  public Customer getCustomerByIdentification(String identification) {
    return this.customerRepository.findCustomerByIdentification(identification)
        .orElseThrow(
            () -> new ServiceException(HttpStatus.NOT_FOUND,
                String.format("El cliente con identificacion [%s] no se ha encontrado", identification)));
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
              String.format("El cliente con identificacion [%s] ya existe", c.getIdentification()));
        });
  }

}
