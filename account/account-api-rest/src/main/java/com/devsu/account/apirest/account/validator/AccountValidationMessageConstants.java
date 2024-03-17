package com.devsu.account.apirest.account.validator;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountValidationMessageConstants {

  public static final String ACCOUNT_TYPE_IS_REQUIRED_MESSAGE = "Account type must be 'Corriente' or 'Ahorros'";

  public static final String CUSTOMER_ID_MIN_MESSAGE = "Customer Id should not be less than 1";

  public static final String INITIAL_BALANCE_VALUE_MESSAGE = "Initial balance must be greater than or equal to 0";

  public static final String ACCOUNT_NUMBER_LENGTH_MESSAGE = "Account number must contain 16 digits";

  public static final String ACTIVE_MESSAGE = "Active must be informed";

}
