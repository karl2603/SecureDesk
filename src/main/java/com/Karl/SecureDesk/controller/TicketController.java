package com.Karl.SecureDesk.controller;

import com.Karl.SecureDesk.dto.CreateTicketRequest;
import com.Karl.SecureDesk.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SecureDesk")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/ticket")
    public ResponseEntity<CreateTicketRequest> createTicket(@Valid @RequestBody CreateTicketRequest ticketRequest){
        ticketService.createTicket(ticketRequest);
        return new ResponseEntity<>(ticketRequest, HttpStatus.CREATED);
    }
}
