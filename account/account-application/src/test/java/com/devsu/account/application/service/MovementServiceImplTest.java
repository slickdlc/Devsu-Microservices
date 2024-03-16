package com.devsu.account.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.devsu.domain.entity.CustomerMovement;
import com.devsu.domain.entity.Movement;
import com.devsu.domain.exception.ServiceException;
import com.devsu.domain.filter.MovementsByCustomerFilter;
import com.devsu.domain.repository.AccountRepository;
import com.devsu.domain.repository.CustomerRepository;
import com.devsu.domain.repository.MovementRepository;
import com.devsu.entity.AccountMother;
import com.devsu.entity.CustomerMother;
import com.devsu.entity.MovementMother;
import com.devsu.filter.MovementsByCustomerFilterMother;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MovementServiceImplTest {

  @InjectMocks
  private MovementServiceImpl movementService;

  @Mock
  private MovementRepository movementRepository;

  @Mock
  private CustomerRepository customerRepository;

  @Mock
  private AccountRepository accountRepository;

  @Nested
  class AddMovement {

    @Test
    void given_accountIdAndAccountNumberNotInformed_addMovement_then_throwIllegalArgumentException() {
      final Movement movement = MovementMother.withAccount(AccountMother.builder()
          .accountId(null)
          .accountNumber(null)
          .build());

      assertThrows(IllegalArgumentException.class, () -> movementService.addMovement(movement));

      verify(MovementServiceImplTest.this.accountRepository, never()).findByAccountId(AccountMother.ACCOUNT_ID);
      verify(MovementServiceImplTest.this.accountRepository, never()).findByAccountNumber(AccountMother.ACCOUNT_NUMBER);
      verify(MovementServiceImplTest.this.movementRepository, never()).findLastMovementTimestamp(any());
      verify(MovementServiceImplTest.this.movementRepository, never()).addLastMovement(any());
    }

    @Test
    void given_accountIdThatIsNotActive_when_addMovement_then_throwServiceException() {
      final Movement movement = MovementMother.withAccount(AccountMother.withAccountId(AccountMother.ACCOUNT_ID));

      when(MovementServiceImplTest.this.accountRepository.findByAccountId(AccountMother.ACCOUNT_ID))
          .thenReturn(Optional.of(AccountMother.withActive(false)));

      assertThrows(ServiceException.class, () -> movementService.addMovement(movement));

      verify(MovementServiceImplTest.this.accountRepository).findByAccountId(AccountMother.ACCOUNT_ID);
      verify(MovementServiceImplTest.this.accountRepository, never()).findByAccountNumber(AccountMother.ACCOUNT_NUMBER);
      verify(MovementServiceImplTest.this.movementRepository, never()).findLastMovementTimestamp(any());
      verify(MovementServiceImplTest.this.movementRepository, never()).addLastMovement(any());
    }

    @Test
    void given_accountNumberThatIsNotActive_when_addMovement_then_throwServiceException() {
      final Movement movement = MovementMother.withAccount(AccountMother.builder()
          .accountId(null)
          .accountNumber(AccountMother.ACCOUNT_NUMBER)
          .build());

      when(MovementServiceImplTest.this.accountRepository.findByAccountNumber(AccountMother.ACCOUNT_NUMBER))
          .thenReturn(Optional.of(AccountMother.withActive(false)));

      assertThrows(ServiceException.class, () -> movementService.addMovement(movement));

      verify(MovementServiceImplTest.this.accountRepository, never()).findByAccountId(AccountMother.ACCOUNT_ID);
      verify(MovementServiceImplTest.this.accountRepository).findByAccountNumber(AccountMother.ACCOUNT_NUMBER);
      verify(MovementServiceImplTest.this.movementRepository, never()).findLastMovementTimestamp(any());
      verify(MovementServiceImplTest.this.movementRepository, never()).addLastMovement(any());
    }

    @Test
    void given_timestampThatIsBeforeThanLastMovement_when_addMovement_then_throwServiceException() {
      final Movement movement = MovementMother.complete();

      when(MovementServiceImplTest.this.accountRepository.findByAccountId(AccountMother.ACCOUNT_ID))
          .thenReturn(Optional.of(AccountMother.complete()));
      when(MovementServiceImplTest.this.movementRepository.findLastMovementTimestamp(AccountMother.ACCOUNT_ID))
          .thenReturn(Optional.of(movement.getTimestamp().plusSeconds(1)));

      assertThrows(ServiceException.class, () -> movementService.addMovement(movement));

      verify(MovementServiceImplTest.this.accountRepository).findByAccountId(AccountMother.ACCOUNT_ID);
      verify(MovementServiceImplTest.this.accountRepository, never()).findByAccountNumber(AccountMother.ACCOUNT_NUMBER);
      verify(MovementServiceImplTest.this.movementRepository).findLastMovementTimestamp(AccountMother.ACCOUNT_ID);
      verify(MovementServiceImplTest.this.movementRepository, never()).addLastMovement(any());
    }

    @Test
    void given_positiveCase_when_addMovement_then_addLastMovement() {
      final Movement movement = MovementMother.complete();

      when(MovementServiceImplTest.this.accountRepository.findByAccountId(AccountMother.ACCOUNT_ID))
          .thenReturn(Optional.of(AccountMother.complete()));
      when(MovementServiceImplTest.this.movementRepository.findLastMovementTimestamp(AccountMother.ACCOUNT_ID))
          .thenReturn(Optional.of(movement.getTimestamp().minusSeconds(1)));

      MovementServiceImplTest.this.movementService.addMovement(movement);

      verify(MovementServiceImplTest.this.accountRepository).findByAccountId(AccountMother.ACCOUNT_ID);
      verify(MovementServiceImplTest.this.accountRepository, never()).findByAccountNumber(AccountMother.ACCOUNT_NUMBER);
      verify(MovementServiceImplTest.this.movementRepository).findLastMovementTimestamp(AccountMother.ACCOUNT_ID);
      verify(MovementServiceImplTest.this.movementRepository).addLastMovement(movement);
    }
  }

  @Nested
  class GetMovementsByFilter {

    @Test
    void given_customerIdThatIsNotActive_when_getMovementsByFilter_then_throwServiceException() {
      final MovementsByCustomerFilter filter = MovementsByCustomerFilterMother.complete();

      when(MovementServiceImplTest.this.customerRepository.findActiveCustomer(CustomerMother.CUSTOMER_ID))
          .thenReturn(Optional.empty());

      assertThrows(ServiceException.class, () -> movementService.getMovementsByFilter(filter));

      verify(MovementServiceImplTest.this.customerRepository).findActiveCustomer(CustomerMother.CUSTOMER_ID);
      verify(MovementServiceImplTest.this.customerRepository, never()).findActiveCustomer(
          CustomerMother.CUSTOMER_IDENTIFICATION);
      verify(MovementServiceImplTest.this.accountRepository, never()).findActiveAccountIds(CustomerMother.CUSTOMER_ID);
      verify(MovementServiceImplTest.this.movementRepository, never()).findMovementsByFilter(any(), any(), any());
    }

    @Test
    void given_customerIdentificationThatIsNotActive_when_getMovementsByFilter_then_throwServiceException() {
      final MovementsByCustomerFilter filter = MovementsByCustomerFilterMother.builder()
          .customerId(null)
          .customerIdentification(CustomerMother.CUSTOMER_IDENTIFICATION)
          .build();

      when(MovementServiceImplTest.this.customerRepository.findActiveCustomer(CustomerMother.CUSTOMER_IDENTIFICATION))
          .thenReturn(Optional.empty());

      assertThrows(ServiceException.class, () -> movementService.getMovementsByFilter(filter));

      verify(MovementServiceImplTest.this.customerRepository, never()).findActiveCustomer(CustomerMother.CUSTOMER_ID);
      verify(MovementServiceImplTest.this.customerRepository).findActiveCustomer(CustomerMother.CUSTOMER_IDENTIFICATION);
      verify(MovementServiceImplTest.this.accountRepository, never()).findActiveAccountIds(CustomerMother.CUSTOMER_ID);
      verify(MovementServiceImplTest.this.movementRepository, never()).findMovementsByFilter(any(), any(), any());
    }

    @Test
    void given_customerIsNotInformed_when_getMovementsByFilter_then_throwIllegalArgumentException() {
      final MovementsByCustomerFilter filter = MovementsByCustomerFilterMother.builder()
          .customerId(null)
          .customerIdentification(null)
          .build();

      assertThrows(IllegalArgumentException.class, () -> movementService.getMovementsByFilter(filter));

      verify(MovementServiceImplTest.this.customerRepository, never()).findActiveCustomer(CustomerMother.CUSTOMER_ID);
      verify(MovementServiceImplTest.this.customerRepository, never()).findActiveCustomer(
          CustomerMother.CUSTOMER_IDENTIFICATION);
      verify(MovementServiceImplTest.this.accountRepository, never()).findActiveAccountIds(CustomerMother.CUSTOMER_ID);
      verify(MovementServiceImplTest.this.movementRepository, never()).findMovementsByFilter(any(), any(), any());
    }

    @Test
    void given_customerIdThatHasMovements_when_getMovementsByFilter_then_returnExpectedList() {
      final MovementsByCustomerFilter filter = MovementsByCustomerFilterMother.complete();
      final List<Movement> movements = List.of(MovementMother.complete());
      final List<Integer> accountIds = List.of(AccountMother.ACCOUNT_ID);

      when(MovementServiceImplTest.this.customerRepository.findActiveCustomer(CustomerMother.CUSTOMER_ID))
          .thenReturn(Optional.of(CustomerMother.complete()));
      when(MovementServiceImplTest.this.accountRepository.findActiveAccountIds(CustomerMother.CUSTOMER_ID))
          .thenReturn(accountIds);
      when(MovementServiceImplTest.this.movementRepository.findMovementsByFilter(filter.getStartDate(), filter.getEndDate(), accountIds))
          .thenReturn(movements);

      final List<CustomerMovement> customerMovements = MovementServiceImplTest.this.movementService.getMovementsByFilter(filter);

      assertNotNull(customerMovements);
      assertEquals(1, customerMovements.size());
      assertEquals(movements.get(0), customerMovements.get(0).getMovement());
      assertEquals(CustomerMother.CUSTOMER_NAME, customerMovements.get(0).getCustomerName());

      verify(MovementServiceImplTest.this.customerRepository).findActiveCustomer(CustomerMother.CUSTOMER_ID);
      verify(MovementServiceImplTest.this.customerRepository, never()).findActiveCustomer(
          CustomerMother.CUSTOMER_IDENTIFICATION);
      verify(MovementServiceImplTest.this.accountRepository).findActiveAccountIds(CustomerMother.CUSTOMER_ID);
      verify(MovementServiceImplTest.this.movementRepository).findMovementsByFilter(filter.getStartDate(), filter.getEndDate(), accountIds);
    }

    @Test
    void given_customerIdentificationThatHasMovements_when_getMovementsByFilter_then_returnExpectedList() {
      final MovementsByCustomerFilter filter = MovementsByCustomerFilterMother.builder()
          .customerId(null)
          .customerIdentification(CustomerMother.CUSTOMER_IDENTIFICATION)
          .build();
      final List<Movement> movements = List.of(MovementMother.complete());
      final List<Integer> accountIds = List.of(AccountMother.ACCOUNT_ID);

      when(MovementServiceImplTest.this.customerRepository.findActiveCustomer(CustomerMother.CUSTOMER_IDENTIFICATION))
          .thenReturn(Optional.of(CustomerMother.complete()));
      when(MovementServiceImplTest.this.accountRepository.findActiveAccountIds(any())).thenReturn(accountIds);
      when(MovementServiceImplTest.this.movementRepository.findMovementsByFilter(filter.getStartDate(), filter.getEndDate(), accountIds))
          .thenReturn(movements);

      final List<CustomerMovement> customerMovements = MovementServiceImplTest.this.movementService.getMovementsByFilter(filter);

      assertNotNull(customerMovements);
      assertEquals(1, customerMovements.size());
      assertEquals(movements.get(0), customerMovements.get(0).getMovement());
      assertEquals(CustomerMother.CUSTOMER_NAME, customerMovements.get(0).getCustomerName());

      verify(MovementServiceImplTest.this.customerRepository, never()).findActiveCustomer(CustomerMother.CUSTOMER_ID);
      verify(MovementServiceImplTest.this.customerRepository).findActiveCustomer(CustomerMother.CUSTOMER_IDENTIFICATION);
      verify(MovementServiceImplTest.this.accountRepository).findActiveAccountIds(CustomerMother.CUSTOMER_ID);
      verify(MovementServiceImplTest.this.movementRepository).findMovementsByFilter(filter.getStartDate(), filter.getEndDate(), accountIds);
    }
  }
}