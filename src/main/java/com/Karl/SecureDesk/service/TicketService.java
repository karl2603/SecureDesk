package com.Karl.SecureDesk.service;

import com.Karl.SecureDesk.dto.CreateTicketRequest;
import com.Karl.SecureDesk.dto.EditTicketRequest;
import com.Karl.SecureDesk.dto.TicketResponse;
import com.Karl.SecureDesk.entity.Ticket;
import com.Karl.SecureDesk.entity.User;
import com.Karl.SecureDesk.repository.TicketRepository;
import com.Karl.SecureDesk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    public TicketResponse getTicket(Long t_id){
        Ticket ticket = ticketRepository.getById(t_id);
        TicketResponse response = new TicketResponse(ticket.getT_id(), ticket.getTitle(), ticket.getDescription(), ticket.getStatus());
        return response;
    }

    public void createTicket(CreateTicketRequest ticketRequest){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        Ticket ticket = new Ticket();
        ticket.setTitle(ticketRequest.getTitle());
        ticket.setDescription(ticketRequest.getDescription());
        ticket.setStatus("ACTIVE");
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setLastUpdatedAt(LocalDateTime.now());
        ticket.setUser(user);
        ticketRepository.save(ticket);
    }

    public void editTicket(EditTicketRequest editRequest, Long t_id){
        Ticket ticket = ticketRepository.findById(t_id).orElseThrow(()-> new RuntimeException("Ticket Not Found"));
        ticket.setTitle(editRequest.getTitle());
        ticket.setDescription(editRequest.getDescription());
        ticket.setLastUpdatedAt(LocalDateTime.now());
        ticketRepository.save(ticket);
    }

    public void deleteTicket(Long t_id){
        Ticket ticket = ticketRepository.getById(t_id);
        ticketRepository.delete(ticket);
    }

}
