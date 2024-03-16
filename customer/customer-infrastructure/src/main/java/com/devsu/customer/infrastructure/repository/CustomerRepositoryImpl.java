package com.devsu.customer.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import com.devsu.customer.infrastructure.mapper.CustomerEntityMapper;
import com.devsu.customer.domain.entity.Customer;
import com.devsu.customer.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

  private final CustomerJpaRepository customerJpaRepository;

  private final CustomerEntityMapper customerMapper;

  @Override
  public void saveCustomer(final Customer customer) {
    this.customerJpaRepository.save(this.customerMapper.fromDomain(customer));
  }

  @Override
  public void deleteCustomerById(final Integer customerId) {
    this.customerJpaRepository.deleteById(customerId);
  }

  @Override
  public List<Customer> readCustomers() {
    return this.customerMapper.toDomain(this.customerJpaRepository.findAll());
  }

  @Override
  public Optional<Customer> findCustomerById(final Integer customerId) {
    return this.customerJpaRepository.findById(customerId).map(this.customerMapper::toDomain);
  }

  @Override
  public Optional<Customer> findCustomerByIdentification(final String identification) {
    return this.customerJpaRepository.findByIdentification(identification).map(this.customerMapper::toDomain);
  }
}
