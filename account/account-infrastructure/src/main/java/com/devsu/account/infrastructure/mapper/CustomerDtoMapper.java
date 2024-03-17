package com.devsu.account.infrastructure.mapper;

import com.devsu.account.infrastructure.dto.CustomerDto;
import com.devsu.domain.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerDtoMapper {

  @Mapping(target = "customerId", source = "clienteId")
  @Mapping(target = "customerName", source = "nombre")
  @Mapping(target = "identification", source = "identificacion")
  Customer toDomain(final CustomerDto customerDto);
}
