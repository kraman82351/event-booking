package com.example.demo.common.constants;

public class AppConstants {
    public static final int TOKEN_EXPIRATION_TIME_MINUTES = 60;
    public static final int REFRESH_TOKEN_EXPIRATION_TIME_MINUTES = 7 * 24 * 60;

    public static final String[] PUBLIC_PATHS = {
            "/user-auth/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/actuator/**"
    };

    public static final String SECURITY_SCHEME_NAME = "bearerAuth";
}
