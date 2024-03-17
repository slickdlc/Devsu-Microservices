package com.devsu.account.apirest.movement.dto;

import static com.devsu.account.apirest.movement.validator.MovementValidationMessageConstants.MIN_CUSTOMER_ID_MESSAGE;
import static com.devsu.account.apirest.movement.validator.MovementValidationMessageConstants.VALID_CUSTOMER_MESSAGE;
import static com.devsu.account.apirest.movement.validator.MovementValidationMessageConstants.VALID_DATE_RANGE_MESSAGE;
import static com.devsu.account.apirest.movement.validator.MovementValidationMessageConstants.VALID_END_DATE_MESSAGE;
import static com.devsu.account.apirest.movement.validator.MovementValidationMessageConstants.VALID_START_DATE_MESSAGE;

import java.time.LocalDate;

import com.devsu.account.apirest.movement.validator.ValidCustomer;
import com.devsu.account.apirest.movement.validator.ValidDates;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidCustomer(message = VALID_CUSTOMER_MESSAGE)
@ValidDates(message = VALID_DATE_RANGE_MESSAGE)
public class MovementsByCustomerRequestDto {

  @NotNull(message = VALID_START_DATE_MESSAGE)
  private LocalDate startDate;

  @NotNull(message = VALID_END_DATE_MESSAGE)
  private LocalDate endDate;

  @Min(value = 1, message = MIN_CUSTOMER_ID_MESSAGE)
  private Integer customerId;

  private String customerIdentification;

}
