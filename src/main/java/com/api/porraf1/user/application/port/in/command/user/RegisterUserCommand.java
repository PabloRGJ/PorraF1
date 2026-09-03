package com.api.porraf1.user.application.port.in.command.user;

/**
 * Comando: intención de registrar un usuario.
 * Inmutable — representa una acción que ya fue solicitada.
 */
public record RegisterUserCommand(
        String name,
        String email,
        String password
) {
    public RegisterUserCommand {
        if(name == null || name.isBlank()) throw new IllegalArgumentException("Name is required");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email is required");
        if (password == null || password.length() < 8) throw new IllegalArgumentException("Password must be at least 8 characters");
    }
}
