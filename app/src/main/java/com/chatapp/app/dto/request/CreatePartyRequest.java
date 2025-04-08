package com.chatapp.app.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public  class CreatePartyRequest {
    private UUID userId;
    private String name;
    public UUID getUserId() { return userId; }
    public String getName() { return name; }
}
