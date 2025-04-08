package com.chatapp.app.controller;



import com.chatapp.app.dto.request.CreatePartyRequest;
import com.chatapp.app.dto.request.JoinPartyRequest;
import com.chatapp.app.model.Party;
import com.chatapp.app.service.PartyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/parties")
public class PartyController {
    private final PartyService partyService;

    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    @PostMapping
    public ResponseEntity<Map<String,Object>> create(@RequestBody CreatePartyRequest req) {
        Party p = partyService.createParty(req.getUserId(), req.getName());
        return ResponseEntity.ok(Map.of("partyId", p.getId(), "code", p.getCode()));
    }

    @PostMapping("/join")
    public ResponseEntity<Map<String,Object>> join(@RequestBody JoinPartyRequest req) {
        Party p = partyService.joinParty(req.getUserId(), req.getCode());
        return ResponseEntity.ok(Map.of("partyId", p.getId(), "code", p.getCode()));
    }

    @PostMapping("/{partyId}/leave")
    public ResponseEntity<Void> leave(@PathVariable UUID partyId, @RequestBody Map<String, UUID> body) {
        partyService.leaveParty(partyId, body.get("userId"));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{partyId}/members/{memberId}")
    public ResponseEntity<Void> remove(@PathVariable UUID partyId, @PathVariable UUID memberId) {
        partyService.removeMember(partyId, memberId);
        return ResponseEntity.noContent().build();
    }


}
