package com.example.demo.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.modules.userModule.core.UserAuthDetails;
import com.example.demo.modules.userModule.request.UserLoginRequest;
import com.example.demo.utility.jwtUtils.JWTUtil;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, StreamReadException, DatabindException, java.io.IOException {

        if (!request.getServletPath().equals("/user-auth/generate-tokens")) {
            filterChain.doFilter(request, response);
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        UserLoginRequest userLoginRequest = objectMapper.readValue(request.getInputStream(), UserLoginRequest.class);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userLoginRequest.getEmail(), userLoginRequest.getPassword());

        Authentication authResult = authenticationManager.authenticate(authToken);

        if (authResult.isAuthenticated()) {
            int TOKEN_EXPIRATION_TIME = 60; // 1 hour
            int REFRESH_TOKEN_EXPIRATION_TIME = 7 * 24 * 60; // 7 days

            String token = jwtUtil.generateToken((UserAuthDetails) authResult.getPrincipal(), TOKEN_EXPIRATION_TIME);
            String refreshToken = jwtUtil.generateToken((UserAuthDetails) authResult.getPrincipal(),
                    REFRESH_TOKEN_EXPIRATION_TIME);

            Cookie tokenCookie = new Cookie("token", token);
            tokenCookie.setHttpOnly(true);
            tokenCookie.setSecure(true);
            tokenCookie.setMaxAge(TOKEN_EXPIRATION_TIME);

            Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
            refreshTokenCookie.setHttpOnly(true);
            refreshTokenCookie.setSecure(true);
            refreshTokenCookie.setPath("/refresh-token");
            refreshTokenCookie.setMaxAge(TOKEN_EXPIRATION_TIME);

            response.addCookie(tokenCookie);
            response.addCookie(refreshTokenCookie);

        }

    }

}
