package com.devsu.account.apirest.movement.mapper;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.devsu.account.apirest.movement.dto.MovementByCustomerResponseDto;
import com.devsu.account.apirest.movement.dto.MovementRequestDto;
import com.devsu.account.apirest.movement.dto.MovementTypeEnum;
import com.devsu.account.apirest.movement.dto.MovementsByCustomerRequestDto;
import com.devsu.domain.entity.CustomerMovement;
import com.devsu.domain.entity.Movement;
import com.devsu.domain.filter.MovementsByCustomerFilter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MovementMapper {

  String PATTERN_FORMAT = "dd/MM/yyyy HH:mm:ss";
  DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
      .withZone(ZoneId.systemDefault());

  List<MovementByCustomerResponseDto> fromDomain(List<CustomerMovement> customerMovements);

  @Mapping(target = "fecha", source = "movement.timestamp", qualifiedByName = "toFormattedDate")
  @Mapping(target = "cliente", source = "customerName")
  @Mapping(target = "numeroCuenta", source = "movement.account.accountNumber")
  @Mapping(target = "tipoMovimiento", source = "movement.movementType")
  @Mapping(target = "saldoInicial", source = "movement.initialBalance")
  @Mapping(target = "estado", source = "movement.account.active")
  @Mapping(target = "movimiento", source = "movement.value")
  @Mapping(target = "saldoDisponible", source = "movement", qualifiedByName = "calculateAvailableBalance")
  MovementByCustomerResponseDto fromDomain(CustomerMovement customerMovement);

  @Named("toFormattedDate")
  default String toFormattedDate(final Instant timestamp) {
    return FORMATTER.format(timestamp);
  }

  @Named("calculateAvailableBalance")
  default BigDecimal calculateAvailableBalance(final Movement movement) {
    return movement.getInitialBalance().add(movement.getValue());
  }

  @Mapping(target = "movementType", source = "value", qualifiedByName = "toMovementType")
  @Mapping(target = "account.accountNumber", source = "accountNumber")
  @Mapping(target = "account.accountId", source = "accountId")
  @Mapping(target = "timestamp", source = "timestamp")
  @Mapping(target = "value", source = "value")
  @Mapping(target = "initialBalance", ignore = true)
  Movement toDomain(MovementRequestDto movementRequestDto);

  @Named("toMovementType")
  default String toMovementType(final BigDecimal value) {
    return value.compareTo(BigDecimal.ZERO) > 0 ?
        MovementTypeEnum.DEPOSIT.getDescription() : MovementTypeEnum.WITHDRAWAL.getDescription();
  }

  MovementsByCustomerFilter toDomain(final MovementsByCustomerRequestDto requestDto);

}
