package com.devsu.account.infrastructure.mapper;

import java.util.List;

import com.devsu.account.infrastructure.entity.MovementEntity;
import com.devsu.domain.entity.Movement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovementEntityMapper {

  MovementEntity fromDomain(final Movement movement);

  Movement toDomain(MovementEntity movementEntity);

  List<Movement> toDomain(List<MovementEntity> movementEntities);
}
