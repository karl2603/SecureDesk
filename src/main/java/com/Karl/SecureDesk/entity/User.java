package com.Karl.SecureDesk.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long u_id;
    private String username;
    private String email;
    private String password;
    private String role;
    private LocalDateTime createdAt;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Ticket> tickets;
}
