package com.book.config.jwt;

import com.book.common.constant.ErrorCode;
import com.book.common.exception.CustomAuthenticationException;
import com.book.config.security.CustomAuthenticationEntryPoint;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtManager jwtManager;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    public JwtFilter(JwtManager jwtManager,
                     CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.jwtManager = jwtManager;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    // Swagger 경로에 대한 예외 처리
    private static final List<String> SWAGGER_PATHS = List.of(
            "/swagger-ui", "/v3/api-docs", "/api-docs", "/adm/auto"
    );

    private boolean isSwaggerPath(String uri) {
        // Swagger UI나 API 문서 경로에 대한 검사
        return SWAGGER_PATHS.stream().anyMatch(uri::startsWith);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = null;

            // Swagger 경로에 대해서는 추가적으로 SUPER 역할만 허용
            if (isSwaggerPath(request.getRequestURI())) {
                Cookie[] cookies = request.getCookies();
				if (cookies != null)
				{
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("ADMIN_ACCESS_TOKEN")) {
							jwt = cookie.getValue();
						}
					}
				}

                if(jwt == null) {
                    throw new CustomAuthenticationException(ErrorCode.LOGIN_REQUIRED);
                }

                if (jwtManager.isExpired(jwt)) {
                    SecurityContextHolder.clearContext();
                    throw new CustomAuthenticationException(ErrorCode.TOKEN_EXPIRED);
                }

                String role = jwtManager.getRole(jwt);

                if (!"SUPER".equals(role)) {
                    throw new CustomAuthenticationException(ErrorCode.SUPER_ROLE_REQUIRED);
                }
            }

            // Authorization 이 없는 경우 통과 (SecurityConfig에서 hasRole로 검증)
            if (jwt == null) {
                filterChain.doFilter(request, response);
                return;
            }

            // 토큰 소멸 시간 검증
            if (jwtManager.isExpired(jwt)) {
                SecurityContextHolder.clearContext();
                throw new CustomAuthenticationException(ErrorCode.TOKEN_EXPIRED); // Spring Security의 AuthenticationException을 상속한 커스텀 예외 사용
            }

            String role = jwtManager.getRole(jwt);

			request.setAttribute("adminPrincipal", role);
        } catch (CustomAuthenticationException e) {
            SecurityContextHolder.clearContext();
            this.customAuthenticationEntryPoint.commence(request, response, e);
            // commence 호출 후 return 해야 함
            return;
        }

        filterChain.doFilter(request, response);
    }

}
