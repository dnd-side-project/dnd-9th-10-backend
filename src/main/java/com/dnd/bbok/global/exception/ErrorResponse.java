package com.dnd.bbok.global.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * of 메서드를 통해 ErrorResponse 응답 형식 지정
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

  private String message;
  private int status;
  private List<FieldError> errors;
  private String code;

  private ErrorResponse(final ErrorCode errorCode, final List<FieldError> errors) {
    this.message = errorCode.getMessage();
    this.status = errorCode.getStatus();
    this.errors = errors;
    this.code = errorCode.getCode();
  }

  private ErrorResponse(final ErrorCode errorCode) {
    this.message = errorCode.getMessage();
    this.status = errorCode.getStatus();
    this.code = errorCode.getCode();
    this.errors = new ArrayList<>();
  }

  public static ErrorResponse of(final ErrorCode errorCode, final BindingResult bindingResult) {
    return new ErrorResponse(errorCode, FieldError.of(bindingResult));
  }

  public static ErrorResponse of(final ErrorCode errorCode) {
    return new ErrorResponse(errorCode);
  }

  public static ErrorResponse of(final ErrorCode errorCode, final List<FieldError> errors) {
    return new ErrorResponse(errorCode, errors);
  }

  public static ErrorResponse of(MethodArgumentTypeMismatchException ex) {
    final String value = Objects.requireNonNull(ex.getValue()).toString();
    final List<ErrorResponse.FieldError> errors =ErrorResponse.FieldError.of(ex.getName(), value, ex.getErrorCode());
    return new ErrorResponse(ErrorCode.INVALID_INPUT_VALUE, errors);
  }

  @Getter
  public static class FieldError {
    private String field;
    private String value;
    private String reason;
    private FieldError(final String field, final String value, final String reason) {
      this.field = field;
      this.value = value;
      this.reason = reason;
    }

    public static List<FieldError> of(final String field, final String value, final String reason) {
      List<FieldError> fieldErrors = new ArrayList<>();
      fieldErrors.add(new FieldError(field, value, reason));
      return fieldErrors;
    }

    private static List<FieldError> of(final BindingResult bindingResult) {
      final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
      return fieldErrors.stream()
          .map(error -> new FieldError(
              error.getField(),
              error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
              error.getDefaultMessage()))
          .collect(Collectors.toList());
    }
  }
}
