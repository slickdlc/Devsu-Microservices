package com.devsu.customer.apirest.customer.mapper;

import java.util.List;

import com.devsu.customer.apirest.customer.dto.CustomerGenderEnumDto;
import com.devsu.customer.apirest.customer.dto.CustomerRequestDto;
import com.devsu.customer.apirest.customer.dto.CustomerResponseDto;
import com.devsu.customer.domain.entity.Customer;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  List<CustomerResponseDto> fromDomain(List<Customer> customers);

  CustomerResponseDto fromDomain(Customer customers);

  @Mapping(target = "customerId", expression = "java(id)")
  @Mapping(target = "personId", ignore = true)
  Customer toDomain(CustomerRequestDto customerRequestDto, @Context Integer id);

  default Customer toDomain(CustomerRequestDto customerRequestDto) {
    return this.toDomain(customerRequestDto, null);
  }

  default CustomerGenderEnumDto toEnum(final String value) {
    return CustomerGenderEnumDto.fromString(value);
  }

}
