package com.devsu.account.apirest.movement.dto;

import static com.devsu.account.apirest.movement.validator.MovementValidationMessageConstants.MIN_ACCOUNT_ID_MESSAGE;
import static com.devsu.account.apirest.movement.validator.MovementValidationMessageConstants.VALID_ACCOUNT_MESSAGE;
import static com.devsu.account.apirest.movement.validator.MovementValidationMessageConstants.VALUE_MUST_NOT_BE_ZERO_MESSAGE;

import java.math.BigDecimal;
import java.time.Instant;

import com.devsu.account.apirest.movement.validator.ValidAccount;
import com.devsu.account.apirest.movement.validator.ValidBalance;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidAccount(message = VALID_ACCOUNT_MESSAGE)
public class MovementRequestDto {

  @Min(value = 1, message = MIN_ACCOUNT_ID_MESSAGE)
  private Integer accountId;

  private String accountNumber;

  private Instant timestamp = Instant.now();

  @ValidBalance(message = VALUE_MUST_NOT_BE_ZERO_MESSAGE)
  private BigDecimal value;

}
