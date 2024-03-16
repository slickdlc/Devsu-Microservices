package com.devsu.account.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.devsu.account.infrastructure.entity.AccountEntityMother;
import com.devsu.account.infrastructure.entity.MovementEntityMother;
import com.devsu.account.infrastructure.mapper.MovementEntityMapperImpl;
import com.devsu.entity.AccountMother;
import com.devsu.entity.MovementMother;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.orm.jpa.JpaSystemException;

@ExtendWith(MockitoExtension.class)
public class MovementRepositoryImplTest {

  @InjectMocks
  private MovementRepositoryImpl movementRepository;

  @Mock
  private MovementJpaRepository movementJpaRepository;

  @Mock
  private AccountJpaRepository accountJpaRepository;

  @Spy
  private MovementEntityMapperImpl movementEntityMapper;

  @Nested
  class AddLastMovement {

    @Test
    void given_positiveCase_when_addLastMovement_then_movementAdded() {
      when(MovementRepositoryImplTest.this.accountJpaRepository.findById(AccountMother.ACCOUNT_ID))
          .thenReturn(Optional.of(AccountEntityMother.complete()));

      MovementRepositoryImplTest.this.movementRepository.addLastMovement(MovementMother.complete());

      verify(MovementRepositoryImplTest.this.accountJpaRepository).findById(any());
      verify(MovementRepositoryImplTest.this.movementJpaRepository).save(any());
      verify(MovementRepositoryImplTest.this.accountJpaRepository).save(any());
    }
  }

  @Nested
  class FindLastMovementTimestamp {

    @Test
    void given_negativeCase_when_findLastMovementTimestamp_then_throwsException() {
      when(MovementRepositoryImplTest.this.movementJpaRepository.findTopByAccountAccountIdOrderByTimestampDesc(AccountMother.ACCOUNT_ID))
          .thenThrow(JpaSystemException.class);

      assertThrows(JpaSystemException.class,
          () -> MovementRepositoryImplTest.this.movementRepository.findLastMovementTimestamp(AccountMother.ACCOUNT_ID));

      verify(MovementRepositoryImplTest.this.movementJpaRepository).findTopByAccountAccountIdOrderByTimestampDesc(any());
    }

    @Test
    void given_positiveCase_when_findLastMovementTimestamp_then_movementTimestampFound() {
      when(MovementRepositoryImplTest.this.movementJpaRepository.findTopByAccountAccountIdOrderByTimestampDesc(AccountMother.ACCOUNT_ID))
          .thenReturn(Optional.of(MovementEntityMother.complete()));

      final var result = MovementRepositoryImplTest.this.movementRepository.findLastMovementTimestamp(AccountMother.ACCOUNT_ID);

      assertNotNull(result);
      assertTrue(result.isPresent());
      verify(MovementRepositoryImplTest.this.movementJpaRepository).findTopByAccountAccountIdOrderByTimestampDesc(any());
    }
  }

  @Nested
  class FindMovementsByFilter {

    @Test
    void given_negativeCase_when_findMovementsByFilter_then_throwsException() {
      when(MovementRepositoryImplTest.this.movementJpaRepository.findByAccountIdsAndTimestampBetween(any(), any(), any()))
          .thenThrow(JpaSystemException.class);

      assertThrows(JpaSystemException.class,
          () -> MovementRepositoryImplTest.this.movementRepository.findMovementsByFilter(null, null, null));
    }

    @Test
    void given_positiveCase_when_findMovementsByFilter_then_movementsFound() {
      when(MovementRepositoryImplTest.this.movementJpaRepository.findByAccountIdsAndTimestampBetween(any(), any(), any()))
          .thenReturn(List.of(MovementEntityMother.complete()));

      final var result = MovementRepositoryImplTest.this.movementRepository.findMovementsByFilter(null, null, null);

      assertNotNull(result);
      assertFalse(result.isEmpty());
      verify(MovementRepositoryImplTest.this.movementJpaRepository).findByAccountIdsAndTimestampBetween(any(), any(), any());
    }
  }
}
