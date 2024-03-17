package com.devsu.account.apirest.account.validator;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountValidationMessageConstants {

  public static final String ACCOUNT_TYPE_IS_REQUIRED_MESSAGE = "El tipo de cuenta tiene que ser o 'Corriente' o 'Ahorros'";

  public static final String CUSTOMER_ID_MIN_MESSAGE = "El id del cliente no debe ser menor que 1";

  public static final String INITIAL_BALANCE_VALUE_MESSAGE = "El saldo inicial debe ser mayor o igual que 0.0";

  public static final String ACCOUNT_NUMBER_LENGTH_MESSAGE = "El número de cuenta debe tener 16 dígitos";

  public static final String ACTIVE_MESSAGE = "El campo activo es obligatorio";

}
