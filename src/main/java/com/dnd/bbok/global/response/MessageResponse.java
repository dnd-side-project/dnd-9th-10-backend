package com.dnd.bbok.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 반환하는 데이터 없이, 상태코드와 메시지만 보내는 응답 형식
 */
@Getter
public class MessageResponse extends DefaultResponse {

  private MessageResponse(HttpStatus status, String message) {
    super(status.value(), message);
  }

  public static MessageResponse of(HttpStatus status, String message) {
    return new MessageResponse(status, message);
  }

}
