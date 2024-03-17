package com.devsu.account.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

  private Integer clienteId;

  private String nombre;

  private String genero;

  private Integer edad;

  private String identificacion;

  private String direccion;

  private String telefono;

  private String contrasena;

  private boolean activo;

}