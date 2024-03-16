package com.devsu.account.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import com.devsu.account.infrastructure.entity.AccountEntity;
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
  public void saveAccount(final Account account) {
    this.accountJpaRepository.save(this.accountMapper.fromDomain(account));
  }

  @Override
  public void deleteAccountById(final Integer accountId) {
    this.accountJpaRepository.deleteById(accountId);
  }

  @Override
  public List<Account> readAccounts() {
    return this.accountMapper.toDomain(this.accountJpaRepository.findAll());
  }

  @Override
  public Optional<Account> findByAccountId(final Integer accountId) {
    return this.accountJpaRepository.findById(accountId).map(this.accountMapper::toDomain);
  }

  @Override
  public Optional<Account> findByAccountNumber(final String accountNumber) {
    return this.accountJpaRepository.findByAccountNumber(accountNumber).map(this.accountMapper::toDomain);
  }

  @Override
  public List<Integer> findActiveAccountIds(final Integer customerId) {
    return this.accountJpaRepository.findAllByCustomerId(customerId).stream()
        .filter(AccountEntity::isActive)
        .map(AccountEntity::getAccountId)
        .toList();
  }

}
