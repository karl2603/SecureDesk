package com.Karl.SecureDesk.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateTicketRequest {
    @NotBlank(message = "Enter Ticket Title")
    private String title;
    @NotBlank(message = "Enter Ticket Description")
    private String description;
}
