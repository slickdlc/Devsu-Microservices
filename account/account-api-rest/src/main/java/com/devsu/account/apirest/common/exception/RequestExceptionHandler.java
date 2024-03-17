package com.devsu.account.apirest.common.exception;

import java.util.HashMap;

import com.devsu.account.apirest.common.dto.FailureMessageDto;
import com.devsu.domain.exception.UseCaseException;
import jakarta.validation.ConstraintViolationException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
      if (err instanceof FieldError) {
        errors.put(((FieldError) err).getField(), err.getDefaultMessage());
      } else {
        errors.put(err.getObjectName(), err.getDefaultMessage());
      }
    }

    return this.handleExceptionInternal(ex, errors, headers, status, request);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public final ResponseEntity<FailureMessageDto> constraintViolationExceptionHandler(final ConstraintViolationException ex) {
    return new ResponseEntity<>(retrieveBadRequestResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UseCaseException.class)
  public final ResponseEntity<FailureMessageDto> useCaseExceptionHandler(final UseCaseException ex) {
    return new ResponseEntity<>(retrieveBadRequestResponse(ex.getMessage()), ex.getStatusCode());
  }


}
