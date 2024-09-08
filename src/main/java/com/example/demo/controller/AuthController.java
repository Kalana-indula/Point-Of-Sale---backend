package com.example.demo.controller;

import com.example.demo.dto.LoginDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    //Accessing login portral
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){

        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt=jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(jwt);
    }

    //Register a user
    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        if(userRepository.existsByUsername(user.getUsername())){
            return ResponseEntity.badRequest().body("Username is already in use");
        }

        if(userRepository.existsByEmail(user.getEmail())){
            return ResponseEntity.badRequest().body("Email is already in use");
        }

        //Create a new 'User' object to get encoded password
        User newUser=new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        return ResponseEntity.ok(userService.createUser(newUser));
    }
}
