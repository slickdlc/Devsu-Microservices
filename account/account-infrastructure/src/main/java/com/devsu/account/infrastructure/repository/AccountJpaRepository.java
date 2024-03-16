package com.devsu.account.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import com.devsu.account.infrastructure.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountJpaRepository extends CrudRepository<AccountEntity, Integer> {

  List<AccountEntity> findAll();
  
  Optional<AccountEntity> findByAccountNumber(String accountNumber);
  
}
