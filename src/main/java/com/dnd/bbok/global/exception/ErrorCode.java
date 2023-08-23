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
  DUPLICATE_CRITERIA(HttpStatus.BAD_REQUEST, "M002", "중복된 기준을 입력할 수 없습니다."),

  // Friend
  CHARACTER_NOT_FOUND(HttpStatus.NOT_FOUND, "F001", "해당 이름을 가진 캐릭터를 찾을 수 없습니다."),
  OTHER_FRIEND_ALREADY_ACTIVE(HttpStatus.BAD_REQUEST, "F002", "동시에 2명 이상의 친구를 생성할 수 없습니다."),
  FRIEND_NOT_FOUND(HttpStatus.BAD_REQUEST, "F003", "해당 아이디를 가진 친구를 찾을 수 없습니다."),
  INVALID_FRIEND_NAME(HttpStatus.BAD_REQUEST, "F004", "친구의 이름은 한글 또는 영문, 숫자의 조합으로 12자 이내로 설정할 수 있습니다."),
  FRIEND_IS_NOT_ACTIVE(HttpStatus.BAD_REQUEST, "F005", "이미 비활성화된 친구입니다."),


  // Diary
  INVALID_MEMBER_CHECKLIST_ID(HttpStatus.BAD_REQUEST, "D001","Member Checklist Id가 올바르지 않습니다."),
  EXCEED_MAX_TAG_COUNT(HttpStatus.BAD_REQUEST, "D002", "친구 당 최대 태그 갯수를 벗어났습니다."),
  DIARY_NOT_FOUND(HttpStatus.NOT_FOUND, "D003", "해당 일기를 찾을 수 없습니다."),

  // JWT
  REFRESH_JWT_EXPIRED(HttpStatus.UNAUTHORIZED, "J001", "만료된 리프레시 토큰입니다."),
  JWT_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "J002", "유효하지 않은 토큰입니다."),
  JWT_EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "J003", "만료된 토큰입니다."),
  JWT_SIGNATURE_INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "J004", "서명이 유효하지 않습니다."),
  JWT_MISSING(HttpStatus.UNAUTHORIZED, "J005", "입력된 토큰이 없습니다."),

  // Saying
  SAYING_NOT_FOUND(HttpStatus.BAD_REQUEST, "S001", "해당 아이디를 가진 명언을 찾을 수 없습니다."),
  ALREADY_BOOKMARKED(HttpStatus.BAD_REQUEST, "S002", "해당 명언은 이미 북마크하셨습니다."),
  BOOKMARK_NOT_FOUND(HttpStatus.BAD_REQUEST, "S003", "북마크 이력이 없습니다.");


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
