package com.devsu.domain.usecase;

import java.util.List;

import com.devsu.domain.entity.CustomerMovement;
import com.devsu.domain.filter.MovementsByCustomerFilter;

public interface GetMovementsUseCase {

  List<CustomerMovement> handle(MovementsByCustomerFilter movementsByCustomerFilter);
}
