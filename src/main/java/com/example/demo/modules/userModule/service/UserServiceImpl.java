package com.example.demo.modules.userModule.service;

import org.springframework.stereotype.Service;

import com.example.demo.entities.user.UserModel;
import com.example.demo.modules.userModule.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(String name, String email, String password) {

        UserModel user = new UserModel();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        userRepository.save(user);
    }
}
