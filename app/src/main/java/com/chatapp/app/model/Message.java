package com.chatapp.app.model;


import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Getter
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @Column(nullable = false)
    private String content;

    private final Instant sentAt = Instant.now();

    // Constructors, getters, setters
    public Message() {}
    public Message(Party party, User sender, String content) {
        this.party = party;
        this.sender = sender;
        this.content = content;
    }
}


