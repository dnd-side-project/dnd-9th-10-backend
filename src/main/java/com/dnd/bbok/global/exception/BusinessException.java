package com.dnd.bbok.global.exception;

import lombok.Getter;

/**
 * 직접 정의한 에러코드와 메시지를 보내기 위해서 해당 클래스를 상속받아야 함
 */
@Getter
public class BusinessException extends RuntimeException {

  private final ErrorCode errorCode;

  public BusinessException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode(){
    return errorCode;
  }

}
