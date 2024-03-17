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
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidAccount(message = VALID_ACCOUNT_MESSAGE)
public class MovementRequestDto {

  @Min(value = 1, message = MIN_ACCOUNT_ID_MESSAGE)
  private Integer cuentaId;

  private String numeroDeCuenta;
  
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
  private Instant fechaMovimiento = Instant.now();

  @ValidBalance(message = VALUE_MUST_NOT_BE_ZERO_MESSAGE)
  private BigDecimal saldo;

}
