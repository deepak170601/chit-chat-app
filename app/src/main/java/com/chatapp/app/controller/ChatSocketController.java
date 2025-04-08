package com.chatapp.app.controller;

import com.chatapp.app.service.ChatService;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class ChatSocketController {
    private final SocketIOServer server;
    private final ChatService chatService;

    public ChatSocketController(SocketIOServer server, ChatService chatService) {
        this.server = server;
        this.chatService = chatService;
    }

    @PostConstruct
    private void init() {
        server.start();
    }

    @OnConnect
    public void onConnect(SocketIOClient client) {
        // optionally authenticate
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        // handle disconnect
    }

    @OnEvent("join")
    public void onJoin(SocketIOClient client, AckRequest ack, Map<String, String> data) {
        UUID partyId = UUID.fromString(data.get("partyId"));
        client.joinRoom(partyId.toString());
        server.getRoomOperations(partyId.toString())
                .sendEvent("userJoined", data);
    }

    @OnEvent("leave")
    public void onLeave(SocketIOClient client, Map<String, String> data) {
        UUID partyId = UUID.fromString(data.get("partyId"));
        client.leaveRoom(partyId.toString());
        server.getRoomOperations(partyId.toString())
                .sendEvent("userLeft", data);
    }

    @OnEvent("message")
    public void onMessage(SocketIOClient client, Map<String, String> data) {
        UUID partyId = UUID.fromString(data.get("partyId"));
        UUID userId = UUID.fromString(data.get("userId"));
        String content = data.get("content");
        chatService.handleMessage(partyId, userId, content);
    }

    @OnEvent("remove")
    public void onRemove(SocketIOClient client, Map<String, String> data) {
        UUID partyId = UUID.fromString(data.get("partyId"));
        String target = data.get("targetUserId");
        server.getRoomOperations(partyId.toString())
                .sendEvent("userRemoved", data);
    }
}