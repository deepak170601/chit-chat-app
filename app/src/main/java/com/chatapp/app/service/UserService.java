package com.chatapp.app.service;// src/main/java/com/example/chat/service/IUserService.java

import com.chatapp.app.model.User;

import java.util.UUID;

public interface UserService {
    User createOrGet(String username);
    User getById(UUID userId);
}
