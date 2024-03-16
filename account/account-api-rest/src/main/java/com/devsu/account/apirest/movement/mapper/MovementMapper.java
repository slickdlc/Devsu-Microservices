package com.devsu.account.apirest.movement.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.devsu.account.apirest.movement.dto.MovementByCustomerResponseDto;
import com.devsu.account.apirest.movement.dto.MovementRequestDto;
import com.devsu.account.apirest.movement.dto.MovementsByCustomerRequestDto;
import com.devsu.domain.entity.CustomerMovement;
import com.devsu.domain.entity.Movement;
import com.devsu.domain.filter.MovementsByCustomerFilter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MovementMapper {

  List<MovementByCustomerResponseDto> fromDomain(List<CustomerMovement> customerMovements);

  @Mapping(target = "fecha", source = "movement.timestamp", dateFormat = "dd/MM/yyyy")
  @Mapping(target = "cliente", source = "customerName")
  @Mapping(target = "numeroCuenta", source = "movement.account.accountNumber")
  @Mapping(target = "tipoMovimiento", source = "movement.movementType")
  @Mapping(target = "saldoInicial", source = "movement.initialBalance")
  @Mapping(target = "estado", source = "movement.account.active")
  @Mapping(target = "movimiento", source = "movement.value")
  @Mapping(target = "saldoDisponible", source = "movement", qualifiedByName = "calculateAvailableBalance")
  MovementByCustomerResponseDto fromDomain(CustomerMovement customerMovement);

  @Named("calculateAvailableBalance")
  default BigDecimal calculateAvailableBalance(final Movement movement) {
    return movement.getInitialBalance().add(movement.getValue());
  }

  @Mapping(target = "movementType", ignore = true)
  @Mapping(target = "account.accountNumber", source = "accountNumber")
  @Mapping(target = "account.accountId", source = "accountId")
  @Mapping(target = "timestamp", source = "timestamp")
  @Mapping(target = "value", source = "value")
  Movement toDomain(MovementRequestDto movementRequestDto);

  MovementsByCustomerFilter toDomain(final MovementsByCustomerRequestDto requestDto);

}
