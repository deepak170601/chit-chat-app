package com.chatapp.app.service.Impl;

import com.chatapp.app.model.Party;
import com.chatapp.app.model.PartyMember;
import com.chatapp.app.model.User;
import com.chatapp.app.repository.PartyMemberRepository;
import com.chatapp.app.repository.PartyRepository;
import com.chatapp.app.service.PartyService;
import com.chatapp.app.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class PartyServiceImpl implements PartyService {

    private final PartyRepository partyRepo;
    private final PartyMemberRepository memberRepo;
    private final UserService userService;
    private final SecureRandom random = new SecureRandom();

    public PartyServiceImpl(PartyRepository partyRepo,
                            PartyMemberRepository memberRepo,
                            UserService userService) {
        this.partyRepo = partyRepo;
        this.memberRepo = memberRepo;
        this.userService = userService;
    }

    private String generateCode() {
        byte[] bytes = new byte[6];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes).substring(0, 8);
    }

    @Override
    @Transactional
    public Party createParty(UUID userId, String name) {
        User admin = userService.getById(userId);
        String code = generateCode();
        Party party = partyRepo.save(new Party(code, name, admin));
        memberRepo.save(new PartyMember(party, admin));
        return party;
    }

    @Override
    @Transactional
    public Party joinParty(UUID userId, String code) {
        Party party = partyRepo.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Invalid code"));
        User user = userService.getById(userId);
        memberRepo.save(new PartyMember(party, user));
        return party;
    }

    @Override
    @Transactional
    public void leaveParty(UUID partyId, UUID userId) {
        memberRepo.deleteByPartyIdAndUserId(partyId, userId);
    }

    @Override
    @Transactional
    public void removeMember(UUID partyId, UUID targetUserId) {
        memberRepo.deleteByPartyIdAndUserId(partyId, targetUserId);
    }

    @Override
    public List<PartyMember> listMembers(UUID partyId) {
        return memberRepo.findByPartyId(partyId);
    }

    @Override
    public Party getById(UUID partyId) {
        return partyRepo.findById(partyId)
                .orElseThrow(() -> new RuntimeException("Party not found"));
    }
}
