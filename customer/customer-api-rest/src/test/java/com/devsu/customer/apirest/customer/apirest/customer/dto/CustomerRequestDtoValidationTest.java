package com.devsu.customer.apirest.customer.apirest.customer.dto;

import static com.devsu.customer.apirest.customer.constants.CustomerValidationMessageConstants.ACTIVE_MESSAGE;
import static com.devsu.customer.apirest.customer.constants.CustomerValidationMessageConstants.ADDRESS_LENGTH_MESSAGE;
import static com.devsu.customer.apirest.customer.constants.CustomerValidationMessageConstants.AGE_MAX_MESSAGE;
import static com.devsu.customer.apirest.customer.constants.CustomerValidationMessageConstants.AGE_MIN_MESSAGE;
import static com.devsu.customer.apirest.customer.constants.CustomerValidationMessageConstants.IDENTIFICATION_LENGTH_MESSAGE;
import static com.devsu.customer.apirest.customer.constants.CustomerValidationMessageConstants.NAME_IS_REQUIRED_MESSAGE;
import static com.devsu.customer.apirest.customer.constants.CustomerValidationMessageConstants.NAME_LENGTH_MESSAGE;
import static com.devsu.customer.apirest.customer.constants.CustomerValidationMessageConstants.PASSWORD_LENGTH_MESSAGE;
import static com.devsu.customer.apirest.customer.constants.CustomerValidationMessageConstants.PHONE_LENGTH_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import com.devsu.customer.apirest.customer.dto.CustomerRequestDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CustomerRequestDtoValidationTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Nested
  class NameField {

    @Test
    void given_customerWithNullName_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<CustomerRequestDto>> violations =
          CustomerRequestDtoValidationTest.this.validator.validate(CustomerRequestDtoMother.withName(null));

      assertEquals(1, violations.size());
      assertEquals(NAME_IS_REQUIRED_MESSAGE, violations.iterator().next().getMessage());
    }

    @Test
    void given_customerWithEmptyName_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<CustomerRequestDto>> violations =
          CustomerRequestDtoValidationTest.this.validator.validate(CustomerRequestDtoMother.withName(""));

      assertEquals(1, violations.size());
      assertEquals(NAME_LENGTH_MESSAGE, violations.iterator().next().getMessage());
    }
  }

  @Nested
  class AgeField {

    @Test
    void given_customerWithNullAge_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<CustomerRequestDto>> violations =
          CustomerRequestDtoValidationTest.this.validator.validate(CustomerRequestDtoMother.withAge(null));

      assertEquals(1, violations.size());
      assertEquals(AGE_MIN_MESSAGE, violations.iterator().next().getMessage());
    }

    @Test
    void given_customerWithNegativeAge_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<CustomerRequestDto>> violations =
          CustomerRequestDtoValidationTest.this.validator.validate(CustomerRequestDtoMother.withAge(-1));

      assertEquals(1, violations.size());
      assertEquals(AGE_MIN_MESSAGE, violations.iterator().next().getMessage());
    }

    @Test
    void given_customerWithTooMuchAge_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<CustomerRequestDto>> violations =
          CustomerRequestDtoValidationTest.this.validator.validate(CustomerRequestDtoMother.withAge(101));

      assertEquals(1, violations.size());
      assertEquals(AGE_MAX_MESSAGE, violations.iterator().next().getMessage());
    }
  }

  @Nested
  class IdentificationField {

    @Test
    void given_customerWithNullIdentification_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<CustomerRequestDto>> violations =
          CustomerRequestDtoValidationTest.this.validator.validate(CustomerRequestDtoMother.withIdentification(null));

      assertEquals(1, violations.size());
      assertEquals(IDENTIFICATION_LENGTH_MESSAGE, violations.iterator().next().getMessage());
    }

    @Test
    void given_customerWithEmptyIdentification_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<CustomerRequestDto>> violations =
          CustomerRequestDtoValidationTest.this.validator.validate(CustomerRequestDtoMother.withIdentification(""));

      assertEquals(1, violations.size());
      assertEquals(IDENTIFICATION_LENGTH_MESSAGE, violations.iterator().next().getMessage());
    }
  }

  @Nested
  class AddressField {

    @Test
    void given_customerWithNullAddress_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<CustomerRequestDto>> violations =
          CustomerRequestDtoValidationTest.this.validator.validate(CustomerRequestDtoMother.withAddress(null));

      assertEquals(1, violations.size());
      assertEquals(ADDRESS_LENGTH_MESSAGE, violations.iterator().next().getMessage());
    }

    @Test
    void given_customerWithEmptyAddress_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<CustomerRequestDto>> violations =
          CustomerRequestDtoValidationTest.this.validator.validate(CustomerRequestDtoMother.withAddress(""));

      assertEquals(1, violations.size());
      assertEquals(ADDRESS_LENGTH_MESSAGE, violations.iterator().next().getMessage());
    }
  }

  @Nested
  class PhoneField {

    @Test
    void given_customerWithNullPhone_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<CustomerRequestDto>> violations =
          CustomerRequestDtoValidationTest.this.validator.validate(CustomerRequestDtoMother.withPhone(null));

      assertEquals(1, violations.size());
      assertEquals(PHONE_LENGTH_MESSAGE, violations.iterator().next().getMessage());
    }

    @Test
    void given_customerWithEmptyPhone_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<CustomerRequestDto>> violations =
          CustomerRequestDtoValidationTest.this.validator.validate(CustomerRequestDtoMother.withPhone(""));

      assertEquals(1, violations.size());
      assertEquals(PHONE_LENGTH_MESSAGE, violations.iterator().next().getMessage());
    }
  }

  @Nested
  class PasswordField {

    @Test
    void given_customerWithNullPassword_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<CustomerRequestDto>> violations =
          CustomerRequestDtoValidationTest.this.validator.validate(CustomerRequestDtoMother.withPassword(null));

      assertEquals(1, violations.size());
      assertEquals(PASSWORD_LENGTH_MESSAGE, violations.iterator().next().getMessage());
    }

    @Test
    void given_customerWithEmptyPassword_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<CustomerRequestDto>> violations =
          CustomerRequestDtoValidationTest.this.validator.validate(CustomerRequestDtoMother.withPassword(""));

      assertEquals(1, violations.size());
      assertEquals(PASSWORD_LENGTH_MESSAGE, violations.iterator().next().getMessage());
    }
  }

  @Nested
  class ActiveField {

    @Test
    void given_customerWithNullActive_when_validate_then_expectedViolations() {
      Set<ConstraintViolation<CustomerRequestDto>> violations =
          CustomerRequestDtoValidationTest.this.validator.validate(CustomerRequestDtoMother.withActive(null));

      assertEquals(1, violations.size());
      assertEquals(ACTIVE_MESSAGE, violations.iterator().next().getMessage());
    }
  }
}
