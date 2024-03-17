package com.devsu.customer.apirest.customer.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerValidationMessageConstants {

  public static final String NAME_IS_REQUIRED_MESSAGE = "El nombre es obligatorio";

  public static final String GENDER_IS_REQUIRED_MESSAGE = "El género debe ser 'F' o 'M'";

  public static final String NAME_LENGTH_MESSAGE = "El nombre debe contener entre 1 y 50 caracteres";

  public static final String AGE_MIN_MESSAGE = "La edad no debe ser menor que 1";

  public static final String AGE_MAX_MESSAGE = "La edad no debe ser mayor que 100";

  public static final String IDENTIFICATION_LENGTH_MESSAGE = "La identificación debe contener 8 caracteres";

  public static final String ADDRESS_LENGTH_MESSAGE = "La dirección debe contener entre 1 y 50 caracteres";

  public static final String PHONE_LENGTH_MESSAGE = "El teléfono debe contener 9 caracteres";

  public static final String PASSWORD_LENGTH_MESSAGE = "El password debe contener entre 8 y 40 caracteres";

  public static final String ACTIVE_MESSAGE = "El campo 'activo' es obligatorio";

}
