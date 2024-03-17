package com.devsu.account.application.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.devsu.domain.entity.Account;
import com.devsu.domain.entity.Customer;
import com.devsu.domain.entity.CustomerMovement;
import com.devsu.domain.entity.Movement;
import com.devsu.domain.exception.ServiceException;
import com.devsu.domain.filter.MovementsByCustomerFilter;
import com.devsu.domain.repository.AccountRepository;
import com.devsu.domain.repository.CustomerRepository;
import com.devsu.domain.repository.MovementRepository;
import com.devsu.domain.service.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {

  private final MovementRepository movementRepository;

  private final AccountRepository accountRepository;

  private final CustomerRepository customerRepository;

  @Override
  public void addMovement(final Movement movement) {
    this.validateToAddLastMovement(movement);
    this.movementRepository.addLastMovement(movement);
  }

  @Override
  public List<CustomerMovement> getMovementsByFilter(final MovementsByCustomerFilter filter) {
    final Customer customer = this.findActiveCustomer(filter);
    final List<Integer> accountIds = this.findAccountIds(customer.getCustomerId());

    return this.movementRepository.findMovementsByFilter(filter.getStartDate(), filter.getEndDate(), accountIds).stream()
        .map(movement -> CustomerMovement.builder().movement(movement).customerName(customer.getCustomerName()).build()).toList();
  }

  private void validateToAddLastMovement(final Movement movement) {
    final Account account = this.findActiveAccount(movement);
    movement.getAccount().setAccountId(account.getAccountId());
    this.validateLastMovement(movement);
    this.validateBalance(account, movement);
  }

  private Account findActiveAccount(final Movement movement) {
    return this.findAccount(movement).filter(Account::isActive).orElseThrow(
        () -> new ServiceException(HttpStatus.NOT_FOUND, "No existe ninguna cuenta activa con el id o número de cuenta proporcionado"));
  }

  private Optional<Account> findAccount(final Movement movement) {
    if (movement.getAccount().getAccountId() != null) {
      return this.accountRepository.findByAccountId(movement.getAccount().getAccountId());
    } else if (movement.getAccount().getAccountNumber() != null) {
      return this.accountRepository.findByAccountNumber(movement.getAccount().getAccountNumber());
    } else {
      throw new IllegalArgumentException("cuentaId y numeroCuenta no deben ser nulos a la vez");
    }
  }

  private void validateLastMovement(final Movement movement) {
    final Instant lastMovementTimestamp =
        this.movementRepository.findLastMovementTimestamp(movement.getAccount().getAccountId()).orElse(null);

    if (lastMovementTimestamp != null && movement.getTimestamp().isBefore(lastMovementTimestamp)) {
      throw new ServiceException(HttpStatus.BAD_REQUEST,
          String.format("La fecha de la transacción no puede ser anterior a la última transacción. Ultima transacción [%s]",
              lastMovementTimestamp));
    }
  }

  private void validateBalance(final Account account, final Movement movement) {
    if (account.getCurrentBalance().add(movement.getValue()).compareTo(BigDecimal.ZERO) < 0) {
      throw new ServiceException(HttpStatus.BAD_REQUEST,
          "No se puede efectuar esta operación. El saldo del movimiento excede el saldo de la cuenta.");
    }
  }

  private Customer findActiveCustomer(final MovementsByCustomerFilter filter) {
    final Optional<Customer> optionalCustomer;
    if (filter.getCustomerId() != null) {
      optionalCustomer = this.customerRepository.findActiveCustomer(filter.getCustomerId());
    } else if (filter.getCustomerIdentification() != null) {
      optionalCustomer = this.customerRepository.findActiveCustomer(filter.getCustomerIdentification());
    } else {
      throw new IllegalArgumentException("customerId y customerIdentification no deben ser nulos a la vez");
    }

    if (optionalCustomer.isEmpty()) {
      throw new ServiceException(HttpStatus.NOT_FOUND, "No existe ningún cliente activo con el id o identificación proporcionado");
    }

    return optionalCustomer.get();
  }

  private List<Integer> findAccountIds(final Integer customerId) {
    return this.accountRepository.findActiveAccountIds(customerId);
  }
}
