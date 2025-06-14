package com.book.common.exception;

import com.book.common.constant.ErrorCode;
import com.book.common.dto.response.CommonErrorResponse;
import com.book.common.dto.response.ErrorResponse;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.net.BindException;
import java.security.SignatureException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 토큰 오류 발생
     */
    @ExceptionHandler({SignatureException.class, MalformedJwtException.class, ClaimJwtException.class, UnsupportedJwtException.class})
    protected ResponseEntity<CommonErrorResponse<ErrorResponse>> handleInvalidTokenException() {
        ErrorCode e = ErrorCode.INVALID_TOKEN;
        log.error("INVALID JWT EXCEPTION: " + e.getMessage());
        ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode(), e.getMessage());
        return new ResponseEntity<>(CommonErrorResponse.ERROR(e.getHttpStatus().value(), errorResponse), e.getHttpStatus());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    protected ResponseEntity<CommonErrorResponse<ErrorResponse>> handleExpiredTokenException()
    {
        ErrorCode e = ErrorCode.TOKEN_EXPIRED;
        log.error("EXPIRED JWT EXCEPTION: " + e.getMessage());
        ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode(), e.getMessage());
        return new ResponseEntity<>(CommonErrorResponse.ERROR(e.getHttpStatus().value(), errorResponse), e.getHttpStatus());
    }

    /**
     * javax.validation.Valid 또는 @Validated binding error가 발생할 경우
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<CommonErrorResponse<ErrorResponse>> handleBindException(BindException e) {
        log.error("handleBindException", e);
        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST.toString(), e.getMessage());
        CommonErrorResponse<ErrorResponse> response = CommonErrorResponse.ERROR(HttpStatus.BAD_REQUEST.value(), errorResponse);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<CommonErrorResponse<ErrorResponse>>
    handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.METHOD_NOT_ALLOWED.toString(), e.getMessage());
        CommonErrorResponse<ErrorResponse> response = CommonErrorResponse.ERROR(HttpStatus.METHOD_NOT_ALLOWED.value(), errorResponse);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * 비즈니스 로직 실행 중 오류 발생
     */
    @ExceptionHandler(value = { BusinessException.class })
    protected ResponseEntity<CommonErrorResponse<ErrorResponse>> handleConflict(BusinessException e) {
        log.error("BusinessException", e);
        ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode().getErrorCode(), e.getMessage());
        CommonErrorResponse<ErrorResponse> response = CommonErrorResponse.ERROR(e.getErrorCode().getHttpStatus().value(), errorResponse);
        return new ResponseEntity<>(response, e.getErrorCode().getHttpStatus());
    }

    /**
     * 시큐리티 로직 실행 중 오류 발생
     */
    @ExceptionHandler(value = { AuthenticationException.class })
    protected ResponseEntity<CommonErrorResponse<ErrorResponse>>  handleConflict(AuthenticationException e) {
        log.error("AuthenticationException", e);
        ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode().getErrorCode(), e.getMessage());
        CommonErrorResponse<ErrorResponse> response = CommonErrorResponse.ERROR(e.getErrorCode().getHttpStatus().value(), errorResponse);
        return new ResponseEntity<>(response, e.getErrorCode().getHttpStatus());
    }

    /**
     * 나머지 예외 발생
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CommonErrorResponse<ErrorResponse>> handleException(Exception e) {
        log.error("Exception", e);
        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.getMessage());
        CommonErrorResponse<ErrorResponse> response = CommonErrorResponse.ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorResponse);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * page not found
     */
    @ExceptionHandler(value = { NoResourceFoundException.class })
    protected ResponseEntity<CommonErrorResponse<ErrorResponse>> handleNotFoundException(NoResourceFoundException e) {
        log.error("NoResourceFoundException: " + e.getMessage() );
        ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.NOT_FOUND.toString(), e.getMessage());
        CommonErrorResponse<ErrorResponse> response = CommonErrorResponse.ERROR(HttpStatus.NOT_FOUND.value(), errorResponse);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
