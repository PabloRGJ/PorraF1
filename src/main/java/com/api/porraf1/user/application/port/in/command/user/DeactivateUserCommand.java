package com.api.porraf1.user.application.port.in.command.user;

import java.util.UUID;

public record DeactivateUserCommand(UUID userId) {
    public DeactivateUserCommand {
        if (userId == null) throw new IllegalArgumentException("UserId is required");
    }
}
