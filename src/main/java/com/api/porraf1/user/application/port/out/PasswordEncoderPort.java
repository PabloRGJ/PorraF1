package com.api.porraf1.user.application.port.out;

/**
 * Puerto de salida para encriptación.
 * El dominio sabe que necesita hashear passwords
 * pero no sabe que por debajo hay BCrypt.
 */
public interface PasswordEncoderPort {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
