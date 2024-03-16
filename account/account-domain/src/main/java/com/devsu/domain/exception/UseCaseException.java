package com.devsu.domain.exception;

import java.io.Serial;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class UseCaseException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 458734573645102L;

  private final HttpStatusCode statusCode;

  public UseCaseException(final ServiceException serviceException) {
    super(serviceException.getMessage(), serviceException);
    this.statusCode = serviceException.getStatusCode();
  }
}