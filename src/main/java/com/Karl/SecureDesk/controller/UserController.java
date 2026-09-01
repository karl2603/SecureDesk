package com.Karl.SecureDesk.controller;

import com.Karl.SecureDesk.dto.LoginRequest;
import com.Karl.SecureDesk.dto.RegisterUserRequest;
import com.Karl.SecureDesk.dto.TicketResponse;
import com.Karl.SecureDesk.dto.UpdateTicketStatusRequest;
import com.Karl.SecureDesk.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //Can only be accessed by users
    @GetMapping("/welcomeHome")
    public String welcomeHome(){
        return "Welcome Home, Raise, View, Update Tickets!";
    }

    //Can be accessed by all
    @PostMapping("/users/register")
    public ResponseEntity<RegisterUserRequest> registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest){
        userService.registerUser(registerUserRequest);
        return new ResponseEntity<>(registerUserRequest, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }

    //Admin Features
    @GetMapping("/tickets")
    public List<TicketResponse> getTickets(){
        return userService.getTickets();
    }

    @GetMapping("/tickets/active")
    public List<TicketResponse> getActiveTickets(){
        return userService.getActiveTickets();
    }

    @PutMapping("/ticket/{t_id}/status")
    public ResponseEntity<String> resolveTicket(@PathVariable("t_id") Long t_id,@Valid @RequestBody UpdateTicketStatusRequest ticketStatusUpdateRequest){
        userService.resolveTicket(t_id, ticketStatusUpdateRequest);
        return new ResponseEntity<>("Ticket id = "+t_id+" Status Updated", HttpStatus.OK);
    }
}
