package com.devsu.customer.infrastructure.mapper;

import java.util.List;

import com.devsu.customer.domain.entity.Customer;
import com.devsu.customer.infrastructure.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

  CustomerEntity fromDomain(final Customer customer);

  @Mapping(target = "personId", ignore = true)
  Customer toDomain(CustomerEntity customerEntity);

  List<Customer> toDomain(List<CustomerEntity> customerEntities);
}
