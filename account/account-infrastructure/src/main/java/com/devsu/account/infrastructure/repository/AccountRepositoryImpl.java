package com.devsu.account.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import com.devsu.account.infrastructure.mapper.AccountEntityMapper;
import com.devsu.domain.entity.Account;
import com.devsu.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

  private final AccountJpaRepository accountJpaRepository;

  private final AccountEntityMapper accountMapper;

  @Override
  public void saveAccount(Account account) {
    this.accountJpaRepository.save(this.accountMapper.fromDomain(account));
  }

  @Override
  public void deleteAccountById(Integer accountId) {
    this.accountJpaRepository.deleteById(accountId);
  }

  @Override
  public List<Account> readAccounts() {
    return this.accountMapper.toDomain(this.accountJpaRepository.findAll());
  }

  @Override
  public Optional<Account> findAccountById(Integer accountId) {
    return this.accountJpaRepository.findById(accountId).map(this.accountMapper::toDomain);
  }

  @Override
  public Optional<Account> findByAccountNumber(String accountNumber) {
    return this.accountJpaRepository.findByAccountNumber(accountNumber).map(this.accountMapper::toDomain);
  }
}
