package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        try {
            List<User> users=userService.findAllUsers();

            if(!users.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body(users);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        try{
            User existingUser=userService.findUserById(id);

            if(existingUser!=null){
                return ResponseEntity.status(HttpStatus.OK).body(existingUser);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No corresponding user found for entered id");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/update/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody User user){
        try {
            User updatedUser=userService.updateUser(id,user);

            if(updatedUser!=null){
                return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existing user found for entered id");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
