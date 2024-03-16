package com.devsu.domain.service;

import java.util.List;

import com.devsu.domain.entity.CustomerMovement;
import com.devsu.domain.entity.Movement;
import com.devsu.domain.filter.MovementsByCustomerFilter;

public interface MovementService {

  void addMovement(final Movement movement);
  
  List<CustomerMovement> getMovementsByFilter(final MovementsByCustomerFilter filter);
}
