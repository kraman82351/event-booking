package com.harshchauhan.irctc_core.modules.userModule.core;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.harshchauhan.irctc_core.entities.user.UserModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAuthDetails implements UserDetails {
    @Positive
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserAuthDetails(UserModel user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getEmail();
        this.password = user.getPassword();
    }
}
