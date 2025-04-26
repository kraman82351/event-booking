package com.harshchauhan.irctc_core.modules.userModule.utils;

import java.util.Map;

import com.harshchauhan.irctc_core.modules.userModule.core.UserAuthDetails;
import com.harshchauhan.irctc_core.utility.jwtUtils.JWTUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class AuthCookieUtil {
    public static Map<String, String> generateAndAddAuthCookies(
            UserAuthDetails userDetails,
            JWTUtil jwtUtil,
            HttpServletResponse response,
            int tokenExpiryMinutes,
            int refreshTokenExpiryMinutes) {
        String token = jwtUtil.generateToken(userDetails, tokenExpiryMinutes);
        String refreshToken = jwtUtil.generateToken(userDetails, refreshTokenExpiryMinutes);

        Cookie tokenCookie = new Cookie("token", token);
        tokenCookie.setHttpOnly(true);
        tokenCookie.setSecure(true);
        tokenCookie.setMaxAge(tokenExpiryMinutes * 60); // convert to seconds

        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setPath("/refresh-token");
        refreshTokenCookie.setMaxAge(refreshTokenExpiryMinutes * 60); // convert to seconds

        response.addCookie(tokenCookie);
        response.addCookie(refreshTokenCookie);

        return Map.of(
                "token", token,
                "refreshToken", refreshToken);
    }
}
