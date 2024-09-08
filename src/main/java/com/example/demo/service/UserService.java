package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User createUser(User user);

    List<User> findAllUsers();

    User findUserById(Long id);

    User updateUser(Long id,User user);

    String deleteUser(Long id);
}
