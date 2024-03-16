package com.devsu.account.apirest.movement.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementByCustomerResponseDto {

  private String fecha;

  private String cliente;

  private String numeroCuenta;

  private String tipoMovimiento;

  private BigDecimal saldoInicial;

  private String estado;

  private BigDecimal movimiento;

  private BigDecimal saldoDisponible;

}
