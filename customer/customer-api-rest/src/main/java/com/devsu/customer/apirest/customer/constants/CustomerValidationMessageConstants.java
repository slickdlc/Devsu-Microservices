package com.devsu.customer.apirest.customer.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerValidationMessageConstants {

  public static final String NAME_IS_REQUIRED_MESSAGE = "Name is required";

  public static final String GENDER_IS_REQUIRED_MESSAGE = "Gender must be 'F' or 'M'";

  public static final String NAME_LENGTH_MESSAGE = "Name must be between 1 and 50 characters";

  public static final String AGE_MIN_MESSAGE = "Age should not be less than 1";

  public static final String AGE_MAX_MESSAGE = "Age should not be greater than 100";

  public static final String IDENTIFICATION_LENGTH_MESSAGE = "Identification must contain 8 characters";

  public static final String ADDRESS_LENGTH_MESSAGE = "Address must contain between 1 and 50 characters";

  public static final String PHONE_LENGTH_MESSAGE = "Phone must contain 9 characters";

  public static final String PASSWORD_LENGTH_MESSAGE = "Password must contain between 8 and 40 characters";

  public static final String ACTIVE_MESSAGE = "Active must be informed";

}
