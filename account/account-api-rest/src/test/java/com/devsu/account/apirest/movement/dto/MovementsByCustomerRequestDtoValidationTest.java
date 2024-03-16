package com.devsu.account.apirest.movement.dto;

import static com.devsu.account.apirest.movement.validator.MovementValidationMessageConstants.MIN_CUSTOMER_ID_MESSAGE;
import static com.devsu.account.apirest.movement.validator.MovementValidationMessageConstants.VALID_CUSTOMER_MESSAGE;
import static com.devsu.account.apirest.movement.validator.MovementValidationMessageConstants.VALID_DATE_RANGE_MESSAGE;
import static com.devsu.account.apirest.movement.validator.MovementValidationMessageConstants.VALID_END_DATE_MESSAGE;
import static com.devsu.account.apirest.movement.validator.MovementValidationMessageConstants.VALID_START_DATE_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import com.devsu.entity.CustomerMother;
import com.devsu.entity.CustomerMovementMother;
import com.devsu.filter.MovementsByCustomerFilterMother;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class MovementsByCustomerRequestDtoValidationTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Nested
  class DateFields {

    @Test
    void given_requestWithNullStartDate_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<MovementsByCustomerRequestDto>> violations =
          MovementsByCustomerRequestDtoValidationTest.this.validator.validate(MovementsByCustomerRequestDtoMother.withStartDate(null));

      assertEquals(2, violations.size());
      final var iterator = violations.iterator();
      assertNotNull(iterator);
      assertEquals(VALID_DATE_RANGE_MESSAGE, iterator.next().getMessage());
      assertEquals(VALID_START_DATE_MESSAGE, iterator.next().getMessage());
    }

    @Test
    void given_requestWithNullEndDate_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<MovementsByCustomerRequestDto>> violations =
          MovementsByCustomerRequestDtoValidationTest.this.validator.validate(MovementsByCustomerRequestDtoMother.withEndDate(null));

      assertEquals(2, violations.size());
      final var iterator = violations.iterator();
      assertNotNull(iterator);
      assertEquals(VALID_DATE_RANGE_MESSAGE, iterator.next().getMessage());
      assertEquals(VALID_END_DATE_MESSAGE, iterator.next().getMessage());
    }

    @Test
    void given_requestWithEndDateBeforeStartDate_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<MovementsByCustomerRequestDto>> violations =
          MovementsByCustomerRequestDtoValidationTest.this.validator.validate(MovementsByCustomerRequestDtoMother
              .withEndDate(MovementsByCustomerFilterMother.START_DATE.minusDays(1)));

      assertEquals(1, violations.size());
      assertEquals(VALID_DATE_RANGE_MESSAGE, violations.iterator().next().getMessage());
    }

  }

  @Nested
  class CustomerFields {

    @Test
    void given_requestWithCustomerFieldsNull_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<MovementsByCustomerRequestDto>> violations =
          MovementsByCustomerRequestDtoValidationTest.this.validator.validate(MovementsByCustomerRequestDtoMother.withCustomerId(null));

      assertEquals(1, violations.size());
      assertEquals(VALID_CUSTOMER_MESSAGE, violations.iterator().next().getMessage());
    }

    @Test
    void given_requestWithBothCustomerFields_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<MovementsByCustomerRequestDto>> violations =
          MovementsByCustomerRequestDtoValidationTest.this.validator.validate(
              MovementsByCustomerRequestDtoMother.withCustomerIdentification(
                  CustomerMother.CUSTOMER_IDENTIFICATION));

      assertEquals(1, violations.size());
      assertEquals(VALID_CUSTOMER_MESSAGE, violations.iterator().next().getMessage());
    }

    @Test
    void given_requestWithNegativeCustomerId_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<MovementsByCustomerRequestDto>> violations =
          MovementsByCustomerRequestDtoValidationTest.this.validator.validate(MovementsByCustomerRequestDtoMother.withCustomerId(-1));

      assertEquals(1, violations.size());
      assertEquals(MIN_CUSTOMER_ID_MESSAGE, violations.iterator().next().getMessage());
    }

   
  }


}
