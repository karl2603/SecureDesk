package com.Karl.SecureDesk.controller;

import com.Karl.SecureDesk.dto.RegisterUserRequest;
import com.Karl.SecureDesk.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SecureDesk")
public class UserController {

    @Autowired
    private UserService userService;
    //Can be accessed by all
    @GetMapping("/home")
    public String home(){
        return "Welcome Home, Create account to raise ticket!";
    }
    //Can be accessed by all
    @GetMapping("/users/register")
    public ResponseEntity<RegisterUserRequest> registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest){
        userService.registerUser(registerUserRequest);
        return new ResponseEntity<>(registerUserRequest, HttpStatus.CREATED);
    }
}
