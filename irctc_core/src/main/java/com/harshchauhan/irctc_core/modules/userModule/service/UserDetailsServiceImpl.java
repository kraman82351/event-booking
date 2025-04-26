package com.harshchauhan.irctc_core.modules.userModule.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.harshchauhan.irctc_core.entities.user.UserModel;
import com.harshchauhan.irctc_core.modules.userModule.core.UserAuthDetails;
import com.harshchauhan.irctc_core.modules.userModule.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserAuthDetails loadUserByUsername(String email) {
        UserModel user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new UserAuthDetails(user);
    }
}
