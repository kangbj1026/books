package com.book.config.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseCookie;

@Builder
@Getter
@Setter
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    private String cookieDomain;

    private ResponseCookie generateCookie(String cookieName, String token)
    {
        return ResponseCookie.from(cookieName, token)
                .domain(cookieDomain)
                .secure(true)
                .sameSite("none")
                .path("/")
                .build();
    }

    public ResponseCookie generateAccessCookie(String accessTokenName)
    {
        return generateCookie(accessTokenName, this.getAccessToken());
    }

    public ResponseCookie generateRefreshCookie(String refreshTokenName)
    {
        return generateCookie(refreshTokenName, this.getRefreshToken());
    }
}
