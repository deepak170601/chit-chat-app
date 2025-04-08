package com.chatapp.app.repository;

import com.chatapp.app.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByPartyIdOrderBySentAtAsc(UUID partyId);
}