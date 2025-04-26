package com.harshchauhan.irctc_core.common.filters;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import com.harshchauhan.irctc_core.common.constants.AppConstants;
import com.harshchauhan.irctc_core.modules.userModule.core.UserAuthDetails;
import com.harshchauhan.irctc_core.modules.userModule.request.UserLoginRequest;
import com.harshchauhan.irctc_core.modules.userModule.utils.AuthCookieUtil;
import com.harshchauhan.irctc_core.utility.jwtUtils.JWTUtil;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
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

        Authentication authentication = authenticationManager.authenticate(authToken);

        if (authentication.isAuthenticated()) {
            AuthCookieUtil.generateAndAddAuthCookies((UserAuthDetails) authentication.getPrincipal(), jwtUtil, response,
                    AppConstants.TOKEN_EXPIRATION_TIME_MINUTES,
                    AppConstants.REFRESH_TOKEN_EXPIRATION_TIME_MINUTES);
        }

    }

}
