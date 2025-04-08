package com.chatapp.app.service;// src/main/java/com/example/chat/service/IPartyService.java

import com.chatapp.app.model.Party;
import com.chatapp.app.model.PartyMember;

import java.util.List;
import java.util.UUID;

public interface PartyService {
    Party createParty(UUID userId, String name);
    Party joinParty(UUID userId, String code);
    void leaveParty(UUID partyId, UUID userId);
    void removeMember(UUID partyId, UUID targetUserId);
    List<PartyMember> listMembers(UUID partyId);
    Party getById(UUID partyId);
}
