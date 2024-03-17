package com.devsu.account.application.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import com.devsu.domain.entity.Account;
import com.devsu.domain.exception.ServiceException;
import com.devsu.domain.repository.AccountRepository;
import com.devsu.domain.repository.CustomerRepository;
import com.devsu.domain.repository.MovementRepository;
import com.devsu.domain.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  private final CustomerRepository customerRepository;

  private final MovementRepository movementRepository;

  @Override
  public void createAccount(final Account account) {
    if (account.getAccountId() != null) {
      throw new IllegalArgumentException("Account id must be null to create a new account");
    }
    this.saveAccount(account);
  }

  @Override
  public void updateAccount(final Account account) {
    this.doIfExistsAndDoesNotHaveMovements(
        account.getAccountId(), accountFound -> this.saveAccount(account)
    );
  }

  @Override
  public void deleteAccount(final Integer accountId) {
    this.doIfExistsAndDoesNotHaveMovements(
        accountId, accountFound -> this.accountRepository.deleteAccountById(accountId)
    );
  }

  @Override
  public List<Account> getAllAccounts() {
    return this.accountRepository.readAccounts();
  }

  @Override
  public Account getAccountById(final Integer accountId) {
    return this.findAccountById(accountId)
        .orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, String.format("Account with id [%s] no se ha encontrado", accountId)));
  }

  private Optional<Account> findAccountById(final Integer accountId) {
    return this.accountRepository.findByAccountId(accountId);
  }

  public void doIfExistsAndDoesNotHaveMovements(final Integer accountId, final Consumer<Account> consumerInstant) {
    this.findAccountById(accountId)
        .ifPresentOrElse(
            (account) -> this.movementRepository.findLastMovementTimestamp(accountId).ifPresentOrElse(
                (lastInstant) -> {
                  throw new ServiceException(HttpStatus.BAD_REQUEST,
                      String.format("Account with id [%s] has movements and cannot be updated or deleted", accountId));
                },
                () -> consumerInstant.accept(account)
            ),
            () -> {
              throw new ServiceException(HttpStatus.NOT_FOUND, String.format("Account with id [%s] no se ha encontrado", accountId));
            });
  }

  private void saveAccount(final Account account) {
    this.validateToSave(account);
    this.accountRepository.saveAccount(account);
  }

  private void validateToSave(final Account account) {
    if (!this.customerRepository.existsActiveCustomer(account.getCustomerId())) {
      throw new ServiceException(HttpStatus.BAD_REQUEST,
          String.format("No existe ningún cliente activo con el id [%s]", account.getCustomerId()));
    }
    this.accountRepository.findByAccountNumber(account.getAccountNumber())
        .filter(accountFound -> !accountFound.getAccountId().equals(account.getAccountId()))
        .ifPresent(c -> {
          throw new ServiceException(HttpStatus.BAD_REQUEST,
              String.format("Ya existe una cuenta asociada al número de cuenta [%s]", c.getAccountNumber()));
        });
  }

}
