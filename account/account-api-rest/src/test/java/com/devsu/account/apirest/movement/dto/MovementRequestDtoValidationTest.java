package com.devsu.account.apirest.movement.dto;

import static com.devsu.account.apirest.movement.validator.MovementValidationMessageConstants.MIN_ACCOUNT_ID_MESSAGE;
import static com.devsu.account.apirest.movement.validator.MovementValidationMessageConstants.VALID_ACCOUNT_MESSAGE;
import static com.devsu.account.apirest.movement.validator.MovementValidationMessageConstants.VALUE_MUST_NOT_BE_ZERO_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Set;

import com.devsu.entity.AccountMother;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class MovementRequestDtoValidationTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Nested
  class AccountFields {

    @Test
    void given_movementWithNullAccountId_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<MovementRequestDto>> violations =
          MovementRequestDtoValidationTest.this.validator.validate(MovementRequestDtoMother.withAccountId(null));

      assertEquals(1, violations.size());
      assertEquals(VALID_ACCOUNT_MESSAGE, violations.iterator().next().getMessage());
    }

    @Test
    void given_movementWithNegativeAccountId_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<MovementRequestDto>> violations =
          MovementRequestDtoValidationTest.this.validator.validate(MovementRequestDtoMother.withAccountId(-1));

      assertEquals(1, violations.size());
      assertEquals(MIN_ACCOUNT_ID_MESSAGE, violations.iterator().next().getMessage());
    }

    @Test
    void given_movementWithEmptyAccountNumber_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<MovementRequestDto>> violations =
          MovementRequestDtoValidationTest.this.validator.validate(MovementRequestDtoMother.builder()
              .accountId(null)
              .accountNumber("")
              .build());

      assertEquals(1, violations.size());
      assertEquals(VALID_ACCOUNT_MESSAGE, violations.iterator().next().getMessage());
    }

    @Test
    void given_movementWithAccountIdAndAccountNumber_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<MovementRequestDto>> violations =
          MovementRequestDtoValidationTest.this.validator.validate(
              MovementRequestDtoMother.withAccountNumber(AccountMother.ACCOUNT_NUMBER));

      assertEquals(1, violations.size());
      assertEquals(VALID_ACCOUNT_MESSAGE, violations.iterator().next().getMessage());
    }
  }

  @Nested
  class ValueField {

    @Test
    void given_movementWithNullValue_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<MovementRequestDto>> violations =
          MovementRequestDtoValidationTest.this.validator.validate(MovementRequestDtoMother.withValue(null));

      assertEquals(1, violations.size());
      assertEquals(VALUE_MUST_NOT_BE_ZERO_MESSAGE, violations.iterator().next().getMessage());
    }

    @Test
    void given_movementWithZeroValue_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<MovementRequestDto>> violations =
          MovementRequestDtoValidationTest.this.validator.validate(MovementRequestDtoMother.withValue(new BigDecimal(0)));

      assertEquals(1, violations.size());
      assertEquals(VALUE_MUST_NOT_BE_ZERO_MESSAGE, violations.iterator().next().getMessage());
    }

  }

}
