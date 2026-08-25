package com.Karl.SecureDesk.service;

import com.Karl.SecureDesk.dto.RegisterUserRequest;
import com.Karl.SecureDesk.dto.TicketResponse;
import com.Karl.SecureDesk.entity.Ticket;
import com.Karl.SecureDesk.entity.User;
import com.Karl.SecureDesk.repository.TicketRepository;
import com.Karl.SecureDesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
