package com.book.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 정상 응답을 표현하는 클래스
 */
@AllArgsConstructor
@Getter
public class CommonResponse<T> {
    private final boolean success;  // 응답 성공 여부
    private final String message;   // 응답 메시지
    private final int statusCode;   // 응답 코드
    private final T data;           // 응답 데이터

    public static <T> CommonResponse<T> OK() {
        return new CommonResponse<>(true, "success", 200, null);
    }

    public static <T> CommonResponse<T> OK(T data) { return new CommonResponse<>(true, "success", 200, data); }

    public static <T> CommonResponse<T> CREATE(T data) {
        return new CommonResponse<>(true, "success", 201, data);
    }

	public static <T> CommonResponse<T> UPDATE(T data) { return new CommonResponse<>(true, "success", 202, data); }

	public static <T> CommonResponse<T> DELETE(T data) { return new CommonResponse<>(true, "success", 205, data); }

	public static <T> CommonResponse<T> BAD() { return new CommonResponse<>(false, "error", 404, null); }

	public static <T> CommonResponse<T> BAD(T data) { return new CommonResponse<>(false, "error", 404, data); }

}
