package com.harshchauhan.irctc_core.common.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import lombok.Getter;

@Getter
public class JWTAuthenticationToken extends AbstractAuthenticationToken {

    private final String token;

    public JWTAuthenticationToken(String token) {
        super(null);
        this.token = token;
        setAuthenticated(false);
    }

    @Override
    public String getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

}
