package com.devsu.account.apirest.movement.controller;

import java.time.LocalDate;
import java.util.List;

import com.devsu.account.apirest.common.BaseController;
import com.devsu.account.apirest.common.dto.SuccessMessageDto;
import com.devsu.account.apirest.movement.dto.MovementByCustomerResponseDto;
import com.devsu.account.apirest.movement.dto.MovementRequestDto;
import com.devsu.account.apirest.movement.dto.MovementsByCustomerRequestDto;
import com.devsu.account.apirest.movement.mapper.MovementMapper;
import com.devsu.domain.usecase.AddMovementUseCase;
import com.devsu.domain.usecase.GetMovementsUseCase;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovementController extends BaseController {

  private static final String ACCOUNT_CREATED_SUCCESSFULLY = "Movement added successfully";

  private final AddMovementUseCase addMovementUseCase;

  private final GetMovementsUseCase getMovementsUseCase;

  private final MovementMapper movementMapper;

  @PostMapping
  public ResponseEntity<SuccessMessageDto> addMovement(@Valid @NotNull @RequestBody final MovementRequestDto requestDto) {
    this.addMovementUseCase.handle(this.movementMapper.toDomain(requestDto));
    return createSuccessMessage(ACCOUNT_CREATED_SUCCESSFULLY);
  }

  @GetMapping("/estado-de-cuenta")
  public ResponseEntity<List<MovementByCustomerResponseDto>> getAccountStatement(
      @RequestParam("fechaInicio") @JsonFormat(pattern = "dd/MM/yyyy") final LocalDate startDate,
      @RequestParam("fechaFin") final LocalDate endDate, @RequestParam("clienteId") final Integer customerId,
      @RequestParam("clienteIdentification") final String customerIdentification) {

    return ok(
        this.movementMapper.fromDomain(
            this.getMovementsUseCase.handle(
                this.movementMapper.toDomain(
                    this.getValidRequest(startDate, endDate, customerId, customerIdentification)))));
  }

  @Valid
  private MovementsByCustomerRequestDto getValidRequest(final LocalDate startDate,
      final LocalDate endDate, final Integer customerId, final String customerIdentification) {
    return MovementsByCustomerRequestDto.builder().startDate(startDate).endDate(endDate)
        .customerId(customerId).customerIdentification(customerIdentification).build();

  }
}
