package com.dnd.bbok.domain.friend.exception;

import com.dnd.bbok.global.exception.BusinessException;
import com.dnd.bbok.global.exception.ErrorCode;

public class CharacterNotFoundException extends BusinessException {
  public CharacterNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }
}
