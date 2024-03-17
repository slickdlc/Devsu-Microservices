package com.devsu.account.apirest.account.mapper;

import java.util.List;

import com.devsu.account.apirest.account.dto.AccountRequestDto;
import com.devsu.account.apirest.account.dto.AccountResponseDto;
import com.devsu.domain.entity.Account;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

  List<AccountResponseDto> fromDomain(List<Account> accounts);

  @Mapping(target = "cuentaId", source = "accountId")
  @Mapping(target = "clienteId", source = "customerId")
  @Mapping(target = "numeroDeCuenta", source = "accountNumber")
  @Mapping(target = "tipoDeCuenta", source = "accountType")
  @Mapping(target = "saldoActual", source = "currentBalance")
  @Mapping(target = "activo", source = "active")
  AccountResponseDto fromDomain(final Account accounts);

  @Mapping(target = "accountId", expression = "java(id)")
  @Mapping(target = "customerId", source = "clienteId")
  @Mapping(target = "accountNumber", source = "numeroDeCuenta")
  @Mapping(target = "accountType", source = "tipoDeCuenta")
  @Mapping(target = "currentBalance", source = "saldoInicial")
  @Mapping(target = "initialBalance", source = "saldoInicial")
  @Mapping(target = "active", source = "activo")
  Account toDomain(AccountRequestDto accountRequestDto, @Context Integer id);

  default Account toDomain(AccountRequestDto accountRequestDto) {
    return this.toDomain(accountRequestDto, null);
  }

}
