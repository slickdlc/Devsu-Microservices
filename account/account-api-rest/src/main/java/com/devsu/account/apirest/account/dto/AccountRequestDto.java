package com.devsu.account.apirest.account.dto;

import static com.devsu.account.apirest.account.constants.AccountValidationMessageConstants.ACCOUNT_NUMBER_LENGTH_MESSAGE;
import static com.devsu.account.apirest.account.constants.AccountValidationMessageConstants.ACCOUNT_TYPE_IS_REQUIRED_MESSAGE;
import static com.devsu.account.apirest.account.constants.AccountValidationMessageConstants.ACTIVE_MESSAGE;
import static com.devsu.account.apirest.account.constants.AccountValidationMessageConstants.CUSTOMER_ID_MIN_MESSAGE;
import static com.devsu.account.apirest.account.constants.AccountValidationMessageConstants.INITIAL_BALANCE_VALUE_MESSAGE;

import java.math.BigDecimal;

import com.devsu.account.apirest.common.validator.AccountTypeSubSet;
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
  private Integer customerId;

  @NotNull(message = ACCOUNT_NUMBER_LENGTH_MESSAGE)
  @Size(min = 16, max = 16, message = ACCOUNT_NUMBER_LENGTH_MESSAGE)
  private String accountNumber;

  @NotNull(message = ACCOUNT_TYPE_IS_REQUIRED_MESSAGE)
  @AccountTypeSubSet(anyOf = {AccountTypeEnumDto.CORRIENTE, AccountTypeEnumDto.AHORROS}, message = ACCOUNT_TYPE_IS_REQUIRED_MESSAGE)
  private String accountType;

  @NotNull(message = INITIAL_BALANCE_VALUE_MESSAGE)
  @DecimalMin(value = "0.0", message = INITIAL_BALANCE_VALUE_MESSAGE)
  private BigDecimal initialBalance;

  @NotNull(message = ACTIVE_MESSAGE)
  private Boolean active;
}
