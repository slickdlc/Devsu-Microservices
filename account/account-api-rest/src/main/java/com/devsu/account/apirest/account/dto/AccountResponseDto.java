package com.devsu.account.apirest.account.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {

  private Integer cuentaId;

  private Integer clienteId;

  private String numeroDeCuenta;

  private String tipoDeCuenta;

  private BigDecimal saldoActual;

  private boolean activo;

}
