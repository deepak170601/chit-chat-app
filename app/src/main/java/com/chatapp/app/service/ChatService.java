package com.chatapp.app.service;// src/main/java/com/example/chat/service/IChatService.java

import java.util.UUID;

public interface ChatService {
    void handleMessage(UUID partyId, UUID userId, String content);
}
