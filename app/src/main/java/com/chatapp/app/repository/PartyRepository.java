package com.chatapp.app.repository;

import com.chatapp.app.model.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface PartyRepository extends JpaRepository<Party, UUID> {
    Optional<Party> findByCode(String code);
}
