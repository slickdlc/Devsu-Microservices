package com.devsu.account.apirest.movement.dto;

import static com.devsu.account.apirest.movement.validator.MovementValidationMessageConstants.MIN_ACCOUNT_ID_MESSAGE;
import static com.devsu.account.apirest.movement.validator.MovementValidationMessageConstants.VALID_ACCOUNT_MESSAGE;
import static com.devsu.account.apirest.movement.validator.MovementValidationMessageConstants.VALID_DATE_FORMAT_MESSAGE;
import static com.devsu.account.apirest.movement.validator.MovementValidationMessageConstants.VALUE_MUST_NOT_BE_ZERO_MESSAGE;

import java.math.BigDecimal;

import com.devsu.account.apirest.movement.validator.ValidAccount;
import com.devsu.account.apirest.movement.validator.ValidBalance;
import com.devsu.account.apirest.movement.validator.ValidDateFormat;
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
  private Integer cuentaId;

  private String numeroDeCuenta;

  @ValidDateFormat(message = VALID_DATE_FORMAT_MESSAGE)
  private String fechaHoraMovimiento;

  @ValidBalance(message = VALUE_MUST_NOT_BE_ZERO_MESSAGE)
  private BigDecimal saldo;

}
