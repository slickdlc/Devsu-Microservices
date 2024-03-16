package com.devsu.domain.repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.devsu.domain.entity.Movement;

public interface MovementRepository {

  void addLastMovement(final Movement movement);

  Optional<Instant> findLastMovementTimestamp(final Integer accountId);

  List<Movement> findMovementsByFilter(final LocalDate startDate, final LocalDate endDate, final List<Integer> accountIds);
}
    
