package com.book.common.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 에러 응답을 표현하는 클래스
 */
@AllArgsConstructor
@Getter
public class CommonErrorResponse<T> {

    private final boolean success;      // 응답 성공 여부
    private final String message;       // 응답 메시지
    private final int statusCode;       // 응답 코드
    private final ErrorResponse error;  // 에러 정보

    public static <T> CommonErrorResponse<T> ERROR(int status, ErrorResponse error) {
        return new CommonErrorResponse<>(false, "fail", status, error);
    }
}