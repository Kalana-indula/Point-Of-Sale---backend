package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        User existingUser=userRepository.findById(id).orElse(null);

        if(existingUser!=null){
            return existingUser;
        }else{
            return null;
        }
    }


    @Override
    public User updateUser(Long id,User user) {
        User existingUser=userRepository.findById(id).orElse(null);

        if(existingUser!=null){
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());

            return userRepository.save(existingUser);
        }else{
            return null;
        }
    }

    @Override
    public String deleteUser(Long id) {

        Boolean isExist=userRepository.existsById(id);

        if(isExist){
            userRepository.deleteById(id);
            return "User deleted successfully";
        }else{
            return "No corresponding user found for entered id";
        }

    }
}
