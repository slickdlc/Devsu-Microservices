package com.devsu.account.apirest.account.mapper;

import java.util.List;

import com.devsu.account.apirest.account.dto.AccountTypeEnumDto;
import com.devsu.account.apirest.account.dto.AccountRequestDto;
import com.devsu.account.apirest.account.dto.AccountResponseDto;
import com.devsu.domain.entity.Account;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

  List<AccountResponseDto> fromDomain(List<Account> accounts);

  AccountResponseDto fromDomain(Account accounts);

  @Mapping(target = "accountId", expression = "java(id)")
  @Mapping(target = "currentBalance", ignore = true)
  Account toDomain(AccountRequestDto accountRequestDto, @Context Integer id);

  default Account toDomain(AccountRequestDto accountRequestDto) {
    return this.toDomain(accountRequestDto, null);
  }

  default AccountTypeEnumDto toEnum(final String value) {
    return AccountTypeEnumDto.fromString(value);
  }

}
