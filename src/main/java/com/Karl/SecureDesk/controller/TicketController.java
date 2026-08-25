package com.Karl.SecureDesk.controller;

import com.Karl.SecureDesk.dto.CreateTicketRequest;
import com.Karl.SecureDesk.dto.EditTicketRequest;
import com.Karl.SecureDesk.dto.TicketResponse;
import com.Karl.SecureDesk.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/SecureDesk")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    //The following functions can be used by users
    @GetMapping("/ticket/{t_id}")
    public TicketResponse getTicket(@PathVariable("t_id") Long t_id){
        return ticketService.getTicket(t_id);
    }

    @PostMapping("/ticket")
    public ResponseEntity<CreateTicketRequest> createTicket(@Valid @RequestBody CreateTicketRequest ticketRequest){
        ticketService.createTicket(ticketRequest);
        return new ResponseEntity<>(ticketRequest, HttpStatus.CREATED);
    }

    @PutMapping("/ticket/{t_id}")
    public ResponseEntity<EditTicketRequest> editTicket(@Valid @RequestBody EditTicketRequest editRequest, @PathVariable("t_id") Long t_id){
        ticketService.editTicket(editRequest, t_id);
        return new ResponseEntity<>(editRequest, HttpStatus.OK);
    }

    @DeleteMapping("/ticket/{t_id}")
    public ResponseEntity<String> deleteTicket(@PathVariable("t_id") Long t_id){
        ticketService.deleteTicket(t_id);
        return new ResponseEntity<>("Ticket Deleted", HttpStatus.OK);
    }
}
