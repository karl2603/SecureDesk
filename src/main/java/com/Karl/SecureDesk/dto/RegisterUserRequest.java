package com.Karl.SecureDesk.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterUserRequest {
    @NotBlank(message = "Enter your name")
    @Length(max = 50)
    private String username;
    @NotBlank(message = "Enter your email")
    @Email(message = "Enter a valid email address")
    @Length(max = 100)
    @Column(unique = true)
    private String email;
    @NotBlank(message = "Enter your password")
    @Length(min = 8, max = 30)
    private String password;
}
