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

  @Mapping(target = "clienteId", source = "customerId")
  @Mapping(target = "nombre", source = "name")
  @Mapping(target = "genero", source = "gender")
  @Mapping(target = "edad", source = "age")
  @Mapping(target = "identificacion", source = "identification")
  @Mapping(target = "direccion", source = "address")
  @Mapping(target = "telefono", source = "phone")
  @Mapping(target = "contrasena", source = "password")
  @Mapping(target = "activo", source = "active")
  CustomerResponseDto fromDomain(final Customer customers);

  @Mapping(target = "customerId", expression = "java(id)")
  @Mapping(target = "personId", ignore = true)
  @Mapping(target = "name", source = "nombre")
  @Mapping(target = "gender", source = "genero")
  @Mapping(target = "age", source = "edad")
  @Mapping(target = "identification", source = "identificacion")
  @Mapping(target = "address", source = "direccion")
  @Mapping(target = "phone", source = "telefono")
  @Mapping(target = "password", source = "contrasena")
  @Mapping(target = "active", source = "activo")
  Customer toDomain(CustomerRequestDto customerRequestDto, @Context Integer id);

  default Customer toDomain(CustomerRequestDto customerRequestDto) {
    return this.toDomain(customerRequestDto, null);
  }

  default CustomerGenderEnumDto toEnum(final String value) {
    return CustomerGenderEnumDto.fromString(value);
  }

}
