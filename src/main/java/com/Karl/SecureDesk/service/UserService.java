package com.Karl.SecureDesk.service;

import com.Karl.SecureDesk.dto.RegisterUserRequest;
import com.Karl.SecureDesk.entity.User;
import com.Karl.SecureDesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public void registerUser(RegisterUserRequest registerUserRequest){
        User user = new User();
        user.setUsername(registerUserRequest.getUsername());
        user.setEmail(registerUserRequest.getEmail());
        user.setPassword(bcryptPasswordEncoder.encode(registerUserRequest.getPassword()));
        user.setRole("USER");
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
    }
}
