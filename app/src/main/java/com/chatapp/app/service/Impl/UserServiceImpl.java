package com.chatapp.app.service.Impl;// src/main/java/com/example/chat/service/impl/UserServiceImpl.java

import com.chatapp.app.model.User;
import com.chatapp.app.repository.UserRepository;
import com.chatapp.app.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public User createOrGet(String username) {
        return userRepo.findByUsername(username)
                .orElseGet(() -> userRepo.save(new User(username)));
    }

    @Override
    public User getById(UUID userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
