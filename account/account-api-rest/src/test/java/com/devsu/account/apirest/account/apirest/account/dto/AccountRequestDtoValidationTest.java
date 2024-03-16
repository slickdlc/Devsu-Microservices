package com.devsu.account.apirest.account.apirest.account.dto;

import static com.devsu.account.apirest.account.constants.AccountValidationMessageConstants.ACCOUNT_NUMBER_LENGTH_MESSAGE;
import static com.devsu.account.apirest.account.constants.AccountValidationMessageConstants.ACCOUNT_TYPE_IS_REQUIRED_MESSAGE;
import static com.devsu.account.apirest.account.constants.AccountValidationMessageConstants.ACTIVE_MESSAGE;
import static com.devsu.account.apirest.account.constants.AccountValidationMessageConstants.CUSTOMER_ID_MIN_MESSAGE;
import static com.devsu.account.apirest.account.constants.AccountValidationMessageConstants.INITIAL_BALANCE_VALUE_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Set;

import com.devsu.account.apirest.account.dto.AccountRequestDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class AccountRequestDtoValidationTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Nested
  class CustomerIdField {

    @Test
    void given_accountWithNullCustomerId_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<AccountRequestDto>> violations =
          AccountRequestDtoValidationTest.this.validator.validate(AccountRequestDtoMother.customerId(null));

      assertEquals(1, violations.size());
      assertEquals(CUSTOMER_ID_MIN_MESSAGE, violations.iterator().next().getMessage());
    }

    @Test
    void given_accountWithNegativeCustomerId_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<AccountRequestDto>> violations =
          AccountRequestDtoValidationTest.this.validator.validate(AccountRequestDtoMother.customerId(-1));

      assertEquals(1, violations.size());
      assertEquals(CUSTOMER_ID_MIN_MESSAGE, violations.iterator().next().getMessage());
    }
  }

  @Nested
  class InitialBalanceField {

    @Test
    void given_accountWithNullInitialBalance_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<AccountRequestDto>> violations =
          AccountRequestDtoValidationTest.this.validator.validate(AccountRequestDtoMother.withInitialBalance(null));

      assertEquals(1, violations.size());
      assertEquals(INITIAL_BALANCE_VALUE_MESSAGE, violations.iterator().next().getMessage());
    }

    @Test
    void given_accountWithNegativeInitialBalance_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<AccountRequestDto>> violations =
          AccountRequestDtoValidationTest.this.validator.validate(AccountRequestDtoMother.withInitialBalance(new BigDecimal(-1)));

      assertEquals(1, violations.size());
      assertEquals(INITIAL_BALANCE_VALUE_MESSAGE, violations.iterator().next().getMessage());
    }

  }

  @Nested
  class AccountNumberField {

    @Test
    void given_accountWithNullAccountNumber_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<AccountRequestDto>> violations =
          AccountRequestDtoValidationTest.this.validator.validate(AccountRequestDtoMother.accountNumber(null));

      assertEquals(1, violations.size());
      assertEquals(ACCOUNT_NUMBER_LENGTH_MESSAGE, violations.iterator().next().getMessage());
    }

    @Test
    void given_accountWithEmptyAccountNumber_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<AccountRequestDto>> violations =
          AccountRequestDtoValidationTest.this.validator.validate(AccountRequestDtoMother.accountNumber(""));

      assertEquals(1, violations.size());
      assertEquals(ACCOUNT_NUMBER_LENGTH_MESSAGE, violations.iterator().next().getMessage());
    }
  }

  @Nested
  class AccountTypeField {

    @Test
    void given_accountWithNullAccountType_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<AccountRequestDto>> violations =
          AccountRequestDtoValidationTest.this.validator.validate(AccountRequestDtoMother.withAccountType(null));

      assertEquals(1, violations.size());
      assertEquals(ACCOUNT_TYPE_IS_REQUIRED_MESSAGE, violations.iterator().next().getMessage());
    }

    @Test
    void given_accountWithEmptyAccountType_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<AccountRequestDto>> violations =
          AccountRequestDtoValidationTest.this.validator.validate(AccountRequestDtoMother.withAccountType(""));

      assertEquals(1, violations.size());
      assertEquals(ACCOUNT_TYPE_IS_REQUIRED_MESSAGE, violations.iterator().next().getMessage());
    }
  }

  @Nested
  class ActiveField {

    @Test
    void given_accountWithNullActive_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<AccountRequestDto>> violations =
          AccountRequestDtoValidationTest.this.validator.validate(AccountRequestDtoMother.withActive(null));

      assertEquals(1, violations.size());
      assertEquals(ACTIVE_MESSAGE, violations.iterator().next().getMessage());
    }
  }
}
