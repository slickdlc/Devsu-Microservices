package com.devsu.account.apirest.account.dto;

import static com.devsu.account.apirest.account.validator.AccountValidationMessageConstants.ACCOUNT_NUMBER_LENGTH_MESSAGE;
import static com.devsu.account.apirest.account.validator.AccountValidationMessageConstants.ACCOUNT_TYPE_IS_REQUIRED_MESSAGE;
import static com.devsu.account.apirest.account.validator.AccountValidationMessageConstants.ACTIVE_MESSAGE;
import static com.devsu.account.apirest.account.validator.AccountValidationMessageConstants.CUSTOMER_ID_MIN_MESSAGE;
import static com.devsu.account.apirest.account.validator.AccountValidationMessageConstants.INITIAL_BALANCE_VALUE_MESSAGE;

import java.math.BigDecimal;

import com.devsu.account.apirest.account.validator.AccountTypeSubSet;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDto {

  @NotNull(message = CUSTOMER_ID_MIN_MESSAGE)
  @Min(value = 1, message = CUSTOMER_ID_MIN_MESSAGE)
  private Integer clienteId;

  @NotNull(message = ACCOUNT_NUMBER_LENGTH_MESSAGE)
  @Size(min = 16, max = 16, message = ACCOUNT_NUMBER_LENGTH_MESSAGE)
  private String numeroDeCuenta;

  @NotNull(message = ACCOUNT_TYPE_IS_REQUIRED_MESSAGE)
  @AccountTypeSubSet(anyOf = {AccountTypeEnumDto.CORRIENTE, AccountTypeEnumDto.AHORROS}, message = ACCOUNT_TYPE_IS_REQUIRED_MESSAGE)
  private String tipoDeCuenta;

  @NotNull(message = INITIAL_BALANCE_VALUE_MESSAGE)
  @DecimalMin(value = "0.0", message = INITIAL_BALANCE_VALUE_MESSAGE)
  private BigDecimal saldoInicial;

  @NotNull(message = ACTIVE_MESSAGE)
  private Boolean activo;
}
