package com.devsu.account.infrastructure.repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.devsu.account.infrastructure.entity.AccountEntity;
import com.devsu.account.infrastructure.entity.MovementEntity;
import com.devsu.account.infrastructure.mapper.MovementEntityMapper;
import com.devsu.domain.entity.Movement;
import com.devsu.domain.repository.MovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class MovementRepositoryImpl implements MovementRepository {

  private final MovementJpaRepository movementJpaRepository;

  private final AccountJpaRepository accountJpaRepository;

  private final MovementEntityMapper movementMapper;

  @Override
  @Transactional
  public void addLastMovement(final Movement movement) {
    final MovementEntity movementEntity = this.movementMapper.fromDomain(movement);
    final AccountEntity accountEntity = this.accountJpaRepository.findById(movementEntity.getAccount().getAccountId()).orElseThrow();
    final Instant now = Instant.now();

    movementEntity.setInitialBalance(accountEntity.getCurrentBalance());

    accountEntity.setCurrentBalance(accountEntity.getCurrentBalance().add(movementEntity.getValue()));
    accountEntity.setUpdatedAt(now);
    this.accountJpaRepository.save(accountEntity);

    movementEntity.setCreatedAt(now);
    movementEntity.setAccount(AccountEntity.builder().accountId(accountEntity.getAccountId()).build());
    this.movementJpaRepository.save(movementEntity);
  }

  @Override
  public Optional<Instant> findLastMovementTimestamp(final Integer accountId) {
    return this.movementJpaRepository.findTopByAccountAccountIdOrderByTimestampDesc(accountId)
        .map(MovementEntity::getTimestamp);
  }

  @Override
  public List<Movement> findMovementsByFilter(final LocalDate startDate, final LocalDate endDate, final List<Integer> accountIds) {
    return this.movementMapper.toDomain(this.movementJpaRepository.findByAccountIdsAndTimestampBetween(accountIds, startDate, endDate));
  }


}
