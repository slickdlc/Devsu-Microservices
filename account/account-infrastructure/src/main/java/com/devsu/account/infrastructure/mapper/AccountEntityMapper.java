package com.devsu.account.infrastructure.mapper;

import java.util.List;

import com.devsu.account.infrastructure.entity.AccountEntity;
import com.devsu.domain.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountEntityMapper {

  @Mapping(target = "updatedAt", ignore = true)
  AccountEntity fromDomain(final Account account);

  Account toDomain(AccountEntity accountEntity);

  List<Account> toDomain(List<AccountEntity> accountEntities);
}
