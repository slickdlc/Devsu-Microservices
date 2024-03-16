package com.devsu.customer.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import com.devsu.customer.infrastructure.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends CrudRepository<CustomerEntity, Integer> {

  List<CustomerEntity> findAll();
  
  Optional<CustomerEntity> findByIdentification(String identification);
  
}
