package com.example.demo.common.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.example.demo.common.token.JWTAuthenticationToken;
import com.example.demo.modules.userModule.core.UserAuthDetails;
import com.example.demo.modules.userModule.service.UserDetailsServiceImpl;
import com.example.demo.utility.jwtUtils.JWTUtil;

public class JWTAuthenticationProvider implements AuthenticationProvider {

    private final JWTUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public JWTAuthenticationProvider(JWTUtil jwtUtil, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsServiceImpl;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = ((JWTAuthenticationToken) authentication).getCredentials();

        String username = (jwtUtil.validateAndExtractData(token)).getUsername();

        if (username == null) {
            throw new BadCredentialsException("Invalid JWT token");
        }

        UserAuthDetails userAuthDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userAuthDetails, null, userAuthDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
