package com.chatapp.app.repository;


import com.chatapp.app.model.PartyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface PartyMemberRepository extends JpaRepository<PartyMember, UUID> {
    List<PartyMember> findByUserId(UUID userId);
    List<PartyMember> findByPartyId(UUID partyId);
    void deleteByPartyIdAndUserId(UUID partyId, UUID userId);
}
