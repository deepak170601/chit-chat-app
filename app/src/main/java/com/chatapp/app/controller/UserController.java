package com.chatapp.app.controller;

import com.chatapp.app.dto.request.CreateUserRequest;
import com.chatapp.app.model.User;
import com.chatapp.app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // Constructor injection of IUserService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint to create or retrieve a user based on username
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest req) {
        User user = userService.createOrGet(req.getUsername());
        return ResponseEntity.ok(user);
    }

    // Endpoint to list parties for a user
    // Currently this is a placeholder; you may implement proper DTO conversion and service method.
    @GetMapping("/{userId}/parties")
    public ResponseEntity<?> listParties(@PathVariable UUID userId) {
        // TODO: Replace with actual service call returning list of parties
        return ResponseEntity.ok("Party list endpoint not implemented yet.");
    }
}
