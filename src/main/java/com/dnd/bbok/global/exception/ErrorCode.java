package com.dnd.bbok.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 직접 정의한 에러코드
 */
@Getter
public enum ErrorCode {

  // Common (공통적으로 사용)
  INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "값이 올바르지 않습니다."),
  METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C002", "지원하지 않는 Http Method 입니다."),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C003", "서버 에러"),
  INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "C004", "입력 값의 타입이 올바르지 않습니다."),
  HANDLE_ACCESS_DENIED(HttpStatus.FORBIDDEN, "C005", "접근이 거부 되었습니다."),
  RESOURCE_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "R002", "인증이 필요합니다. 로그인을 해주세요."),
  RESOURCE_FORBIDDEN(HttpStatus.FORBIDDEN, "R001", "해당 리소스에 대한 권한이 없습니다."),

  // Member
  MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "M001", "해당 uuid를 가진 멤버를 찾을 수 없습니다."),

  // Friend
  CHARACTER_NOT_FOUND(HttpStatus.NOT_FOUND, "F001", "해당 이름을 가진 캐릭터를 찾을 수 없습니다."),
  OTHER_FRIEND_ALREADY_ACTIVE(HttpStatus.BAD_REQUEST, "F002", "동시에 2명 이상의 친구를 생성할 수 없습니다."),

  // JWT
  REFRESH_JWT_EXPIRED(HttpStatus.UNAUTHORIZED, "J003", "만료된 리프레시 토큰입니다.");



  private final HttpStatus status;
  private final String code;
  private final String message;

  public int getStatus() {
    return this.status.value();
  }

  ErrorCode(HttpStatus status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }

}
