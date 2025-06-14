package com.book.config.security;

import com.book.config.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors
                        .configurationSource(corsConfigurationSource()))
                .headers(header -> header
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(auth -> auth
                        // 정적 파일 관련 요청
                        .requestMatchers(
                                PathRequest.toStaticResources().atCommonLocations(),
                                AntPathRequestMatcher.antMatcher("/fonts/**"),
                                AntPathRequestMatcher.antMatcher("/img/**"),
                                AntPathRequestMatcher.antMatcher("/uploads/**")
                        ).permitAll()
                        // API 관련 요청
                        .requestMatchers(
                                AntPathRequestMatcher.antMatcher("/api/v1/auth/**"),
                                AntPathRequestMatcher.antMatcher("/api/v1/upload/**"),
                                AntPathRequestMatcher.antMatcher("/api/v1/sso/**")
                        ).permitAll()
                        .requestMatchers(
                                AntPathRequestMatcher.antMatcher("/api/v1/admin/**")
                        ).hasAnyAuthority("ADMIN","SUPER")
                        .requestMatchers(
                                AntPathRequestMatcher.antMatcher("/api-docs/**"),
                                AntPathRequestMatcher.antMatcher("/v3/api-docs/**"),
                                AntPathRequestMatcher.antMatcher("/adm/auto/**")
                        ).hasAnyAuthority("SUPER")
                        .anyRequest().permitAll()
                )

                // JWT 토큰 방식을 사용하기 때문에 세션방식은 사용하지 않도록 설정
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .formLogin(AbstractHttpConfigurer::disable)
                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("/api/admin/auth/logout")
                        .deleteCookies("ACCESS_TOKEN", "REFRESH_TOKEN")
                );

        // 권한 예외가 발생했을 경우 핸들링
        http
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                );
        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(Collections.singletonList("*")); // addAllowedOriginPattern("*") 대신 사용
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
