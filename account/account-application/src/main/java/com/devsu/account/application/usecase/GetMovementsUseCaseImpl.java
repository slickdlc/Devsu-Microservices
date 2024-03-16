package com.devsu.account.application.usecase;

import java.util.List;

import com.devsu.domain.entity.CustomerMovement;
import com.devsu.domain.exception.ServiceException;
import com.devsu.domain.exception.UseCaseException;
import com.devsu.domain.filter.MovementsByCustomerFilter;
import com.devsu.domain.service.MovementService;
import com.devsu.domain.usecase.GetMovementsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMovementsUseCaseImpl implements GetMovementsUseCase {

  private final MovementService movementService;

  @Override
  public List<CustomerMovement> handle(final MovementsByCustomerFilter movementsByCustomerFilter) {
    try {
      return this.movementService.getMovementsByFilter(movementsByCustomerFilter);
    } catch (ServiceException e) {
      throw new UseCaseException(e);
    }
  }
}
