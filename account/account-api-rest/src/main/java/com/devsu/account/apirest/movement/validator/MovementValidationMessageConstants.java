package com.devsu.account.apirest.movement.validator;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MovementValidationMessageConstants {

  public static final String VALID_ACCOUNT_MESSAGE = "Account must be informed: accountId or accountNumber. One or the other, not both and neither";

  public static final String MIN_ACCOUNT_ID_MESSAGE = "Account id must be greater than 0";

  public static final String MIN_CUSTOMER_ID_MESSAGE = "Customer id must be greater than 0";

  public static final String VALUE_MUST_NOT_BE_ZERO_MESSAGE = "Value must not be 0";

  public static final String VALID_CUSTOMER_MESSAGE = "Customer must be informed: customerId or customerIdentification. One or the other, not both and neither";
  
  public static final String VALID_START_DATE_MESSAGE = "Start date must be informed";
  
  public static final String VALID_END_DATE_MESSAGE = "End date must be informed";
  
  public static final String VALID_DATE_RANGE_MESSAGE = "End date must be greater than start date";

}
