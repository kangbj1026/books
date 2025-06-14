package com.book.config.security;

import com.book.common.constant.ErrorCode;
import com.book.common.dto.response.CommonErrorResponse;
import com.book.common.dto.response.ErrorResponse;
import com.book.common.exception.CustomAuthenticationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Spring Security 권한 에러 발생 시 처리 커스텀
 */
@RequiredArgsConstructor
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;



    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		ErrorCode errorCode = ErrorCode.LOGIN_REQUIRED; // 기본적으로 LOGIN_REQUIRED 에러 코드 사용

		// CustomAuthenticationException일 경우
		if (authException instanceof CustomAuthenticationException customAuthException) {
			errorCode = customAuthException.getErrorCode(); // 커스텀 에러 코드를 사용
		}

		// ErrorResponse 응답 처리
		ErrorResponse errorResponse = ErrorResponse.of(errorCode.getErrorCode(), errorCode.getMessage());

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		PrintWriter writer = response.getWriter();
		writer.write(objectMapper.writeValueAsString(CommonErrorResponse.ERROR(HttpServletResponse.SC_UNAUTHORIZED, errorResponse)));
		writer.flush();
    }
}
