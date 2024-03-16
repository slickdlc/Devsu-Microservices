package com.devsu.account.infrastructure.mapper;

import java.util.List;

import com.devsu.domain.entity.Account;
import com.devsu.account.infrastructure.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountEntityMapper {

  AccountEntity fromDomain(final Account account);

  Account toDomain(AccountEntity accountEntity);

  List<Account> toDomain(List<AccountEntity> accountEntities);
}
