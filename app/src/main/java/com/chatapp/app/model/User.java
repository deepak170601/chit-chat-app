package com.chatapp.app.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    @Setter
    @Column(nullable = false)
    private String username;

    private final Instant createdAt = Instant.now();

    // Constructors, getters, setters
    public User() {}
    public User(String username) { this.username = username; }

}

