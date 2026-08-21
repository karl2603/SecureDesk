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
public class EditTicketRequest {
    @NotBlank(message = "Enter ticket title")
    private String title;
    @NotBlank(message = "Enter ticket description")
    private String description;
}
