package com.Karl.SecureDesk.service;

import com.Karl.SecureDesk.dto.CreateTicketRequest;
import com.Karl.SecureDesk.entity.Ticket;
import com.Karl.SecureDesk.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public void createTicket(CreateTicketRequest ticketRequest){
        Ticket ticket = new Ticket();
        ticket.setTitle(ticketRequest.getTitle());
        ticket.setDescription(ticketRequest.getDescription());
        ticket.setStatus("ACTIVE");
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setLastUpdatedAt(LocalDateTime.now());
        ticketRepository.save(ticket);
    }
}
