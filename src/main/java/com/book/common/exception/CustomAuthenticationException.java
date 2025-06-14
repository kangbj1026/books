package com.book.common.exception;


import com.book.common.constant.ErrorCode;
import lombok.Getter;
import org.springframework.security.core.AuthenticationException; // Spring Security의 AuthenticationException 사용

@Getter
public class CustomAuthenticationException extends AuthenticationException {
    private final ErrorCode errorCode;

    public CustomAuthenticationException(ErrorCode errorCode) {
        super(errorCode.getMessage()); // 메시지를 부모 클래스에 전달
        this.errorCode = errorCode;
    }
}