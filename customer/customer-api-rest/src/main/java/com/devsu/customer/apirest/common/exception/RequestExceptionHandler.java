package com.devsu.customer.apirest.common.exception;

import java.util.HashMap;

import com.devsu.customer.apirest.common.dto.FailureMessageDto;
import com.devsu.domain.exception.UseCaseException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RequestExceptionHandler extends ResponseEntityExceptionHandler {

  private static FailureMessageDto retrieveBadRequestResponse(final String message) {
    return FailureMessageDto.builder()
        .message(message)
        .build();
  }

  protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
      @NonNull final HttpHeaders headers,
      @NonNull final HttpStatusCode status, @NonNull final WebRequest request) {

    var errors = new HashMap<>();
    for (var err : ex.getBindingResult().getAllErrors()) {
      errors.put(((FieldError) err).getField(), err.getDefaultMessage());
    }

    return this.handleExceptionInternal(ex, errors, headers, status, request);
  }

  @ExceptionHandler(UseCaseException.class)
  public final ResponseEntity<FailureMessageDto> useCaseExceptionHandler(final UseCaseException ex) {
    return new ResponseEntity<>(retrieveBadRequestResponse(ex.getMessage()), ex.getStatusCode());
  }


}
