package com.devsu.account.infrastructure.mapper;

import java.util.List;

import com.devsu.account.infrastructure.entity.MovementEntity;
import com.devsu.domain.entity.Movement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovementEntityMapper {

  @Mapping(target = "movementId", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "account.updatedAt", ignore = true)
  MovementEntity fromDomain(final Movement movement);

  Movement toDomain(MovementEntity movementEntity);

  List<Movement> toDomain(List<MovementEntity> movementEntities);
}
