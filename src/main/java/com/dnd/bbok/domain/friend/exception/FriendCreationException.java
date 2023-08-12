package com.dnd.bbok.domain.friend.exception;

import com.dnd.bbok.global.exception.BusinessException;
import com.dnd.bbok.global.exception.ErrorCode;

public class FriendCreationException extends BusinessException {

  public FriendCreationException(ErrorCode errorCode) {
    super(errorCode);
  }

}
