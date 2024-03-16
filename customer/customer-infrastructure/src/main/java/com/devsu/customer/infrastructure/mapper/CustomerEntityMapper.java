package com.devsu.customer.infrastructure.mapper;

import java.util.List;

import com.devsu.customer.domain.entity.Customer;
import com.devsu.customer.infrastructure.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

  CustomerEntity fromDomain(Customer customer);

  Customer toDomain(CustomerEntity customerEntity);

  List<Customer> toDomain(List<CustomerEntity> customerEntities);
}
