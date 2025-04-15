package com.example.demo.config;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.common.authenticationToken.JWTAuthenticationToken;
import com.example.demo.utility.jwtUtils.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTValidationFilter extends OncePerRequestFilter {

    JWTUtil jwtUtil;
    AuthenticationManager authenticationManager;

    public JWTValidationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String token = jwtUtil.extractJwtFromRequest(request);

        if (token != null) {

            JWTAuthenticationToken jwtAuthenticationToken = new JWTAuthenticationToken(token);
            Authentication authentication = authenticationManager.authenticate(jwtAuthenticationToken);

            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

}
