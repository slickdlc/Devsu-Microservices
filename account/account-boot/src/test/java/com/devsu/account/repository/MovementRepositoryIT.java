package com.devsu.account.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.devsu.account.config.ApplicationAbstractIT;
import com.devsu.domain.repository.MovementRepository;
import com.devsu.entity.AccountMother;
import com.devsu.entity.MovementMother;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MovementRepositoryIT extends ApplicationAbstractIT {

  private static final Integer ACCOUNT_ID_WITH_MOVEMENTS = 6;

  @Autowired
  private MovementRepository movementRepository;

  @Nested
  class AddLastMovement {

    private static final Integer ACCOUNT_ID_TO_ADD_MOVEMENTS = 4;

    @Test
    void given_positiveCase_when_addLastMovement_then_success() {
      assertDoesNotThrow(() -> MovementRepositoryIT.this.movementRepository.addLastMovement(
          MovementMother.withAccount(AccountMother.withAccountId(ACCOUNT_ID_TO_ADD_MOVEMENTS))));
    }
  }

  @Nested
  class FindLastMovementTimestamp {

    private static final Integer ACCOUNT_ID_WITHOUT_MOVEMENTS = 5;

    @Test
    void given_accountIdWithoutMovements_when_findLastMovementTimestamp_then_success() {
      final var result = MovementRepositoryIT.this.movementRepository.findLastMovementTimestamp(ACCOUNT_ID_WITHOUT_MOVEMENTS);

      assertNotNull(result);
      assertTrue(result.isEmpty());
    }

    @Test
    void given_accountIdWithMovements_when_findLastMovementTimestamp_then_success() {
      final var result = MovementRepositoryIT.this.movementRepository.findLastMovementTimestamp(ACCOUNT_ID_WITH_MOVEMENTS);

      assertNotNull(result);
      assertTrue(result.isPresent());
    }
  }

  @Nested
  class FindMovementsByFilter {

    private static final String EXPECTED_MOVEMENT_TYPE = "Retiro";

    private static final BigDecimal EXPECTED_VALUE = new BigDecimal("-0.9500000000");

    @Test
    void given_positiveCase_when_findMovementsByFilter_then_success() {
      final LocalDate startDate = LocalDate.of(2024, 3, 1);
      final LocalDate endDate = LocalDate.of(2024, 4, 1);
      final var result =
          MovementRepositoryIT.this.movementRepository.findMovementsByFilter(startDate, endDate, List.of(ACCOUNT_ID_WITH_MOVEMENTS));

      assertNotNull(result);
      assertFalse(result.isEmpty());
      assertEquals(1, result.size());
      assertEquals(ACCOUNT_ID_WITH_MOVEMENTS, result.get(0).getAccount().getAccountId());
      assertEquals(EXPECTED_MOVEMENT_TYPE, result.get(0).getMovementType());
      assertEquals(EXPECTED_VALUE, result.get(0).getValue());
    }
  }
}
