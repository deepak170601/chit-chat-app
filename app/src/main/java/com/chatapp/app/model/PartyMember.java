package com.chatapp.app.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Entity
@Table(name = "party_members",
        uniqueConstraints = @UniqueConstraint(columnNames = {"party_id", "user_id"}))
public class PartyMember {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private final Instant joinedAt = Instant.now();

    // Constructors, getters, setters
    public PartyMember() {}
    public PartyMember(Party party, User user) {
        this.party = party;
        this.user = user;
    }
}
