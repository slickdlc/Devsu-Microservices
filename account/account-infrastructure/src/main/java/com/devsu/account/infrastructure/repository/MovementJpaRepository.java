package com.devsu.account.infrastructure.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.devsu.account.infrastructure.entity.MovementEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementJpaRepository extends CrudRepository<MovementEntity, Integer> {

  List<MovementEntity> findAll();

  Optional<MovementEntity> findTopByAccountAccountIdOrderByTimestampDesc(final Integer accountId);

  @Query("""
      SELECT m
      FROM MovementEntity m
      WHERE m.account.accountId IN :accountIds
      AND m.timestamp BETWEEN :startDate AND :endDate
      ORDER BY m.timestamp DESC, m.id DESC
      """)
  List<MovementEntity> findByAccountIdsAndTimestampBetween(final List<Integer> accountIds, final LocalDate startDate,
      final LocalDate endDate);

}
