package com.devsu.account.apirest.account.controller;

import java.util.List;

import com.devsu.account.apirest.account.dto.AccountRequestDto;
import com.devsu.account.apirest.account.dto.AccountResponseDto;
import com.devsu.account.apirest.account.dto.AccountToPatchRequestDto;
import com.devsu.account.apirest.account.mapper.AccountMapper;
import com.devsu.account.apirest.common.BaseController;
import com.devsu.account.apirest.common.dto.SuccessMessageDto;
import com.devsu.domain.usecase.CreateAccountUseCase;
import com.devsu.domain.usecase.DeleteAccountUseCase;
import com.devsu.domain.usecase.FindAccountUseCase;
import com.devsu.domain.usecase.GetAllAccountsUseCase;
import com.devsu.domain.usecase.PatchAccountUseCase;
import com.devsu.domain.usecase.UpdateAccountUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class AccountController extends BaseController {

  private static final String ACCOUNT_CREATED_SUCCESSFULLY = "Cuenta creada satisfactoriamente";

  private static final String ACCOUNT_UPDATED_SUCCESSFULLY = "Cuenta modificada satisfactoriamente";

  private static final String ACCOUNT_DELETED_SUCCESSFULLY = "Cuenta eliminada satisfactoriamente";

  private final CreateAccountUseCase createAccountUseCase;

  private final GetAllAccountsUseCase getAllAccountsUseCase;

  private final FindAccountUseCase findAccountUseCase;

  private final UpdateAccountUseCase updateAccountUseCase;

  private final PatchAccountUseCase patchAccountUseCase;

  private final DeleteAccountUseCase deleteAccountUseCase;

  private final AccountMapper accountMapper;

  @GetMapping
  public ResponseEntity<List<AccountResponseDto>> getAllAccounts() {
    return ok(this.accountMapper.fromDomain(this.getAllAccountsUseCase.handle()));
  }

  @PostMapping
  public ResponseEntity<SuccessMessageDto> createAccount(@Valid @NotNull @RequestBody final AccountRequestDto requestDto) {
    this.createAccountUseCase.handle(this.accountMapper.toDomain(requestDto));
    return createSuccessMessage(ACCOUNT_CREATED_SUCCESSFULLY);
  }

  @PutMapping("/{id}")
  public ResponseEntity<SuccessMessageDto> updateAccount(@Valid @NotNull @RequestBody final AccountRequestDto requestDto,
      @Valid @NotNull @PathVariable("id") Integer id) {
    this.updateAccountUseCase.handle(this.accountMapper.toDomain(requestDto, id));
    return createSuccessMessage(ACCOUNT_UPDATED_SUCCESSFULLY);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<SuccessMessageDto> patchAccount(@Valid @NotNull @RequestBody final AccountToPatchRequestDto requestDto,
      @Valid @NotNull @PathVariable("id") Integer id) {
    this.patchAccountUseCase.handle(this.accountMapper.toDomain(requestDto, id));
    return createSuccessMessage(ACCOUNT_UPDATED_SUCCESSFULLY);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<SuccessMessageDto> deleteAccount(@Valid @NotNull @PathVariable("id") Integer id) {
    this.deleteAccountUseCase.handle(id);
    return createSuccessMessage(ACCOUNT_DELETED_SUCCESSFULLY);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AccountResponseDto> getAccount(@Valid @NotNull @PathVariable("id") Integer id) {
    return ok(this.accountMapper.fromDomain(this.findAccountUseCase.handle(id)));
  }

}
