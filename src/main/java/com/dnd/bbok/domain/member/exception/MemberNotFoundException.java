package com.dnd.bbok.domain.member.exception;

import com.dnd.bbok.global.exception.BusinessException;
import com.dnd.bbok.global.exception.ErrorCode;

public class MemberNotFoundException extends BusinessException {
  public MemberNotFoundException(ErrorCode errorCode) {
    super(errorCode);
  }
}
