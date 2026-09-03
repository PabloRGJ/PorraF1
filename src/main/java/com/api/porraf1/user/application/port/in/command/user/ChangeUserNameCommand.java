package com.api.porraf1.user.application.port.in.command.user;

import java.util.UUID;

public record ChangeUserNameCommand(
        UUID userId,
        String userName
) {
    public ChangeUserNameCommand {
        if (userId == null) throw new IllegalArgumentException("UserId is required");
        if (userName == null) throw new IllegalArgumentException("UserName is required");
    }
}
