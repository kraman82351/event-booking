package com.example.demo.modules.userModule.service;

import org.springframework.stereotype.Service;

import com.example.demo.entities.user.UserModel;
import com.example.demo.modules.userModule.repository.UserRepository;
import com.example.demo.utility.passwordUtils.PasswordEncryptUtils;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncryptUtils passwordEncryptUtils;

    public UserServiceImpl(UserRepository userRepository, PasswordEncryptUtils passwordEncryptUtils) {
        this.userRepository = userRepository;
        this.passwordEncryptUtils = passwordEncryptUtils;
    }

    @Override
    public void createUser(String name, String email, String password) {

        UserModel user = new UserModel();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncryptUtils.encryptPassword(password));

        userRepository.save(user);
    }
}
