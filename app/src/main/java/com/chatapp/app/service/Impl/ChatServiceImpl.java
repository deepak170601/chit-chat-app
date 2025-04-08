package com.chatapp.app.service.Impl;

import com.chatapp.app.repository.MessageRepository;
import com.chatapp.app.service.ChatService;

import com.chatapp.app.service.PartyService;
import com.chatapp.app.service.UserService;
import com.corundumstudio.socketio.SocketIOServer;
import com.chatapp.app.model.Message;
import com.chatapp.app.model.Party;
import com.chatapp.app.model.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ChatServiceImpl implements ChatService {

    private final SocketIOServer server;
    private final MessageRepository messageRepo;
    private final PartyService partyService;
    private final UserService userService;

    public ChatServiceImpl(SocketIOServer server,
                           MessageRepository messageRepo,
                           PartyService partyService,
                           UserService userService) {
        this.server = server;
        this.messageRepo = messageRepo;
        this.partyService = partyService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void handleMessage(UUID partyId, UUID userId, String content) {
        Party party = partyService.getById(partyId);
        User user = userService.getById(userId);
        Message msg = messageRepo.save(new Message(party, user, content));
        // Broadcast to all members in the party room
        server.getRoomOperations(partyId.toString())
                .sendEvent("newMessage", msg);
    }
}
