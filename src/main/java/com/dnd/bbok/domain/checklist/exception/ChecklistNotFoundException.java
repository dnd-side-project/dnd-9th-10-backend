package com.dnd.bbok.domain.checklist.exception;

import com.dnd.bbok.global.exception.BusinessException;
import com.dnd.bbok.global.exception.ErrorCode;

public class ChecklistNotFoundException extends BusinessException {
    public ChecklistNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}