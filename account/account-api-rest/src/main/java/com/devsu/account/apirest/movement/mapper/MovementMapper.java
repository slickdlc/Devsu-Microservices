package com.devsu.account.apirest.movement.mapper;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.devsu.account.apirest.movement.dto.MovementByCustomerResponseDto;
import com.devsu.account.apirest.movement.dto.MovementRequestDto;
import com.devsu.account.apirest.movement.dto.MovementTypeEnum;
import com.devsu.account.apirest.movement.dto.MovementsByCustomerRequestDto;
import com.devsu.domain.entity.CustomerMovement;
import com.devsu.domain.entity.Movement;
import com.devsu.domain.filter.MovementsByCustomerFilter;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.validation.annotation.Validated;

@Validated
@Mapper(componentModel = "spring")
public interface MovementMapper {

  String PATTERN_FORMAT = "dd/MM/yyyy HH:mm:ss";
  DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
      .withZone(ZoneId.systemDefault());

  List<MovementByCustomerResponseDto> fromDomain(List<CustomerMovement> customerMovements);

  @Mapping(target = "fecha", source = "movement.timestamp", qualifiedByName = "toFormattedDate")
  @Mapping(target = "cliente", source = "customerName")
  @Mapping(target = "numeroDeCuenta", source = "movement.account.accountNumber")
  @Mapping(target = "tipo", source = "movement.account.accountType")
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

  @Mapping(target = "movementType", source = "saldo", qualifiedByName = "toMovementType")
  @Mapping(target = "account.accountNumber", source = "numeroDeCuenta")
  @Mapping(target = "account.accountId", source = "cuentaId")
  @Mapping(target = "timestamp", source = "fechaHoraMovimiento", qualifiedByName = "toInstant")
  @Mapping(target = "value", source = "saldo")
  @Mapping(target = "initialBalance", ignore = true)
  Movement toDomain(MovementRequestDto movementRequestDto);

  @Named("toInstant")
  default Instant toInstant(final String fechaHoraMovimiento) {
    if (fechaHoraMovimiento == null) {
      return Instant.now();
    } else {
      LocalDateTime localDateTime = LocalDateTime.parse(fechaHoraMovimiento, FORMATTER);
      ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
      return zonedDateTime.toInstant();
    }
  }

  @Named("toMovementType")
  default String toMovementType(final BigDecimal value) {
    return value.compareTo(BigDecimal.ZERO) > 0 ?
        MovementTypeEnum.DEPOSIT.getDescription() : MovementTypeEnum.WITHDRAWAL.getDescription();
  }

  MovementsByCustomerFilter toDomain(@Valid final MovementsByCustomerRequestDto requestDto);

}
