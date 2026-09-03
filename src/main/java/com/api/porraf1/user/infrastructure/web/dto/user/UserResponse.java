package com.api.porraf1.user.infrastructure.web.dto.user;

import com.api.porraf1.user.domain.model.User;

import java.util.UUID;

/**
 * DTO de respuesta — traduce el modelo de dominio a lo que ve el cliente.
 * Nunca expone passwordHash.
 */
public record UserResponse(
        UUID id,
        String userName,
        String email,
        boolean active
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.isActive()
        );
    }
}
