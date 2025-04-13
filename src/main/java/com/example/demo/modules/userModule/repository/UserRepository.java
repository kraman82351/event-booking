package com.example.demo.modules.userModule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.user.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

}
