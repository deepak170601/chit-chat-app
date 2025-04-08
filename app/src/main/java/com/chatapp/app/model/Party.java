package com.chatapp.app.model;


import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Getter
@Entity
@Table(name = "parties")
public class Party {
    @Id
    @GeneratedValue
    private UUID id;

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Set<PartyMember> getMembers() {
        return members;
    }

    public void setMembers(Set<PartyMember> members) {
        this.members = members;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(unique = true, length = 8, nullable = false)
    private String code;

    private String name;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;

    private final Instant createdAt = Instant.now();

    @OneToMany(mappedBy = "party", cascade = CascadeType.ALL)
    private Set<PartyMember> members;

    // Constructors, getters, setters
    public Party() {}
    public Party(String code, String name, User admin) {
        this.code = code;
        this.name = name;
        this.admin = admin;
    }
}