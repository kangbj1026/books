package com.book.common.util;

import com.book.common.constant.ErrorCode;
import com.book.common.exception.AuthenticationException;
import com.book.config.jwt.JwtManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RequiredArgsConstructor
@Component
public class JwtUtil {
	private final JwtManager jwtManager;

	public Long getAdminUID()
	{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String authorization = request.getHeader("Authorization");
		if (authorization == null || !authorization.startsWith("Bearer ")) {
			return null;
		}

		// Bearer 부분 제거 후 순수 토큰만 획득
		String adminToken = authorization.split(" ")[1];

		// 토큰 소멸 시간 검증
		if (jwtManager.isExpired(adminToken)) {
			return null;
		}

		// 토큰에서 userUID 획득
		return jwtManager.getAdminUID(adminToken);
	}

}