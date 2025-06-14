package com.book.common.exception;

import com.book.common.constant.ErrorCode;
import lombok.Getter;

@Getter
public class AuthenticationException extends BusinessException {

    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
