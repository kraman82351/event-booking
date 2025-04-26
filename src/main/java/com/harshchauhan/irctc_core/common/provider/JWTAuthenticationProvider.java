package com.harshchauhan.irctc_core.common.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.harshchauhan.irctc_core.common.token.JWTAuthenticationToken;
import com.harshchauhan.irctc_core.modules.userModule.core.UserAuthDetails;
import com.harshchauhan.irctc_core.modules.userModule.service.UserDetailsServiceImpl;
import com.harshchauhan.irctc_core.utility.jwtUtils.JWTUtil;

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
