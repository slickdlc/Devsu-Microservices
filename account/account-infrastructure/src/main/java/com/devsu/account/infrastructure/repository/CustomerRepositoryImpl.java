package com.devsu.account.infrastructure.repository;

import java.util.Optional;

import com.devsu.account.infrastructure.dto.CustomerDto;
import com.devsu.account.infrastructure.mapper.CustomerDtoMapper;
import com.devsu.domain.entity.Customer;
import com.devsu.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

  private static final String PATH_FIND_CUSTOMER_BY_ID = "/clientes/%s";

  private static final String PATH_FIND_CUSTOMER_BY_IDENTIFICATION = "/clientes/identification/%s";

  private final RestTemplate customerRestTemplate;

  private final CustomerDtoMapper customerDtoMapper;

  @Override
  public Optional<Customer> findActiveCustomer(final String customerIdentification) {
    return this.findActiveCustomerNameByUrl(String.format(PATH_FIND_CUSTOMER_BY_IDENTIFICATION, customerIdentification));
  }

  @Override
  public Optional<Customer> findActiveCustomer(final Integer customerId) {
    return this.findActiveCustomerNameByUrl(String.format(PATH_FIND_CUSTOMER_BY_ID, customerId));
  }

  @Override
  public boolean existsActiveCustomer(final String customerIdentification) {
    return this.findActiveCustomerByUrl(String.format(PATH_FIND_CUSTOMER_BY_IDENTIFICATION, customerIdentification)).isPresent();
  }

  @Override
  public boolean existsActiveCustomer(final Integer customerId) {
    return this.findActiveCustomerByUrl(String.format(PATH_FIND_CUSTOMER_BY_ID, customerId)).isPresent();
  }

  private Optional<Customer> findActiveCustomerNameByUrl(final String url) {
    return this.findActiveCustomerByUrl(url).map(this.customerDtoMapper::toDomain);
  }

  private Optional<CustomerDto> findActiveCustomerByUrl(final String url) {
    try {
      return Optional.ofNullable(
              this.customerRestTemplate.getForEntity(url,
                  CustomerDto.class).getBody())
          .filter(CustomerDto::isActive);

    } catch (final NotFound e) {
      return Optional.empty();
    } catch (final Exception e) {
      log.error("Error in customer microservice", e);
      return Optional.empty();
    }
  }

}
