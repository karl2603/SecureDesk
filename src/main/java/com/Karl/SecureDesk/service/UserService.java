package com.Karl.SecureDesk.service;

import com.Karl.SecureDesk.dto.LoginRequest;
import com.Karl.SecureDesk.dto.RegisterUserRequest;
import com.Karl.SecureDesk.dto.TicketResponse;
import com.Karl.SecureDesk.dto.UpdateTicketStatusRequest;
import com.Karl.SecureDesk.entity.Ticket;
import com.Karl.SecureDesk.entity.User;
import com.Karl.SecureDesk.repository.TicketRepository;
import com.Karl.SecureDesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

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

    public String login(LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = authManager.authenticate(authRequest);
        if(authentication.isAuthenticated()) return jwtService.generateToken(loginRequest.getUsername());
        else return "User not authenticated";
    }

    public List<TicketResponse> getTickets(){
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketResponse> response = new ArrayList<TicketResponse>();
        for(int i=0; i<tickets.size(); i++){
            Ticket ticket = tickets.get(i);
            TicketResponse ticketResponse = new TicketResponse(ticket.getT_id(), ticket.getTitle(), ticket.getDescription(), ticket.getStatus());
            response.add(ticketResponse);
        }
        return response;
    }

    public List<TicketResponse> getActiveTickets(){
        List<Ticket> tickets = ticketRepository.findActiveTickets();
        List<TicketResponse> response = new ArrayList<TicketResponse>();
        for(int i=0; i<tickets.size(); i++){
            Ticket ticket = tickets.get(i);
            TicketResponse ticketResponse = new TicketResponse(ticket.getT_id(), ticket.getTitle(), ticket.getDescription(), ticket.getStatus());
            response.add(ticketResponse);
        }
        return response;
    }

    public void resolveTicket(Long t_id, UpdateTicketStatusRequest ticketStatusUpdateRequest){
        Ticket ticket = ticketRepository.getById(t_id);
        ticket.setStatus(ticketStatusUpdateRequest.getStatus());
        ticketRepository.save(ticket);
    }
}
