package com.devsu.customer.apirest.customer.controller;

import java.util.List;

import com.devsu.customer.apirest.common.BaseController;
import com.devsu.customer.apirest.common.dto.SuccessMessageDto;
import com.devsu.customer.apirest.customer.dto.CustomerRequestDto;
import com.devsu.customer.apirest.customer.dto.CustomerResponseDto;
import com.devsu.customer.apirest.customer.mapper.CustomerMapper;
import com.devsu.customer.domain.usecase.CreateCustomerUseCase;
import com.devsu.customer.domain.usecase.DeleteCustomerUseCase;
import com.devsu.customer.domain.usecase.FindCustomerUseCase;
import com.devsu.customer.domain.usecase.GetAllCustomersUseCase;
import com.devsu.customer.domain.usecase.UpdateCustomerUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class CustomerController extends BaseController {

  private static final String CUSTOMER_CREATED_SUCCESSFULLY = "Customer created successfully";

  private static final String CUSTOMER_UPDATED_SUCCESSFULLY = "Customer updated successfully";

  private static final String CUSTOMER_DELETED_SUCCESSFULLY = "Customer deleted successfully";

  private final CreateCustomerUseCase createCustomerUseCase;

  private final GetAllCustomersUseCase getAllCustomersUseCase;

  private final FindCustomerUseCase findCustomerUseCase;

  private final UpdateCustomerUseCase updateCustomerUseCase;

  private final DeleteCustomerUseCase deleteCustomerUseCase;

  private final CustomerMapper customerMapper;

  @GetMapping
  public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
    return ok(this.customerMapper.fromDomain(this.getAllCustomersUseCase.handle()));
  }

  @PostMapping
  public ResponseEntity<SuccessMessageDto> createCustomer(@Valid @NotNull @RequestBody final CustomerRequestDto requestDto) {
    this.createCustomerUseCase.handle(this.customerMapper.toDomain(requestDto));
    return createSuccessMessage(CUSTOMER_CREATED_SUCCESSFULLY);
  }

  @PutMapping("/{id}")
  public ResponseEntity<SuccessMessageDto> updateCustomer(@Valid @NotNull @RequestBody final CustomerRequestDto requestDto,
      @Valid @NotNull @PathVariable("id") Integer id) {
    this.updateCustomerUseCase.handle(this.customerMapper.toDomain(requestDto, id));
    return createSuccessMessage(CUSTOMER_UPDATED_SUCCESSFULLY);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<SuccessMessageDto> deleteCustomer(@Valid @NotNull @PathVariable("id") Integer id) {
    this.deleteCustomerUseCase.handle(id);
    return createSuccessMessage(CUSTOMER_DELETED_SUCCESSFULLY);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerResponseDto> getCustomer(@Valid @NotNull @PathVariable("id") final Integer id) {
    return ok(this.customerMapper.fromDomain(this.findCustomerUseCase.handle(id)));
  }

  @GetMapping("/identification/{id}")
  public ResponseEntity<CustomerResponseDto> getCustomer(@Valid @NotNull @PathVariable("id") final String identification) {
    return ok(this.customerMapper.fromDomain(this.findCustomerUseCase.handle(identification)));
  }

}
