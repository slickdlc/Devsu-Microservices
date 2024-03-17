package com.devsu.account.apirest.movement.validator;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MovementValidationMessageConstants {

  public static final String VALID_ACCOUNT_MESSAGE =
      "La cuenta es obligatoria: cuentaId o numeroDeCuenta. Se debe informar uno de ellos solamente.";

  public static final String MIN_ACCOUNT_ID_MESSAGE = "cuentaId tiene que ser mayor que 0";

  public static final String MIN_CUSTOMER_ID_MESSAGE = "clienteId tiene que ser mayor que 0";

  public static final String VALUE_MUST_NOT_BE_ZERO_MESSAGE = "El saldo no puede ser 0";

  public static final String VALID_CUSTOMER_MESSAGE =
      "El cliente es obligatorio: clienteId o clienteIdentificacion. Se debe informar uno de ellos solamente.";

  public static final String VALID_START_DATE_MESSAGE = "La fecha de inicio es obligatoria";

  public static final String VALID_END_DATE_MESSAGE = "La fecha de fin es obligatoria";

  public static final String VALID_DATE_RANGE_MESSAGE = "La fecha de inicio debe ser antes que la fecha de fin";
  
  public static final String VALID_DATE_FORMAT_MESSAGE = "La fecha y hora del movimiento debe tener el formato yyyy-MM-dd HH:mm:ss";

}
