package com.harshchauhan.irctc_core.common.filters;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.harshchauhan.irctc_core.common.constants.AppConstants;
import com.harshchauhan.irctc_core.common.token.JWTAuthenticationToken;
import com.harshchauhan.irctc_core.utility.jwtUtils.JWTUtil;

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

        String path = request.getServletPath();
        for (String publicPath : AppConstants.PUBLIC_PATHS) {
            if (path.startsWith(publicPath.replace("**", ""))) {
                filterChain.doFilter(request, response);
                return;
            }
        }

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
