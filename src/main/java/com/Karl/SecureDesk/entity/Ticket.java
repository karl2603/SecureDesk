package com.Karl.SecureDesk.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long t_id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "u_id")
    private User user;
}
