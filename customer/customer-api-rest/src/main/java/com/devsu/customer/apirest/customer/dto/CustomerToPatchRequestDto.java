package com.devsu.customer.apirest.customer.dto;

import static com.devsu.customer.apirest.customer.constants.CustomerValidationMessageConstants.ADDRESS_LENGTH_MESSAGE;
import static com.devsu.customer.apirest.customer.constants.CustomerValidationMessageConstants.AGE_MAX_MESSAGE;
import static com.devsu.customer.apirest.customer.constants.CustomerValidationMessageConstants.AGE_MIN_MESSAGE;
import static com.devsu.customer.apirest.customer.constants.CustomerValidationMessageConstants.GENDER_IS_REQUIRED_MESSAGE;
import static com.devsu.customer.apirest.customer.constants.CustomerValidationMessageConstants.IDENTIFICATION_LENGTH_MESSAGE;
import static com.devsu.customer.apirest.customer.constants.CustomerValidationMessageConstants.NAME_LENGTH_MESSAGE;
import static com.devsu.customer.apirest.customer.constants.CustomerValidationMessageConstants.PASSWORD_LENGTH_MESSAGE;
import static com.devsu.customer.apirest.customer.constants.CustomerValidationMessageConstants.PHONE_LENGTH_MESSAGE;

import com.devsu.customer.apirest.common.validator.ValidCustomerGender;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerToPatchRequestDto {

  @Size(min = 1, max = 50, message = NAME_LENGTH_MESSAGE)
  private String nombre;

  @ValidCustomerGender(anyOf = {CustomerGenderEnumDto.FEMALE, CustomerGenderEnumDto.MALE}, message = GENDER_IS_REQUIRED_MESSAGE)
  private String genero;

  @Min(value = 1, message = AGE_MIN_MESSAGE)
  @Max(value = 100, message = AGE_MAX_MESSAGE)
  private Integer edad;

  @Size(min = 8, max = 8, message = IDENTIFICATION_LENGTH_MESSAGE)
  private String identificacion;

  @Size(min = 1, max = 50, message = ADDRESS_LENGTH_MESSAGE)
  private String direccion;

  @Size(min = 9, max = 9, message = PHONE_LENGTH_MESSAGE)
  private String telefono;

  @Size(min = 8, max = 40, message = PASSWORD_LENGTH_MESSAGE)
  private String contrasena;
}
