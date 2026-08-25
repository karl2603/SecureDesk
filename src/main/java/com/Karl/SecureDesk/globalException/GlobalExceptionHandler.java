package com.Karl.SecureDesk.globalException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler()
    public String TicketDoesNotExist(Exception e){
        return "Ticket Does Not Exist";
    }
}
