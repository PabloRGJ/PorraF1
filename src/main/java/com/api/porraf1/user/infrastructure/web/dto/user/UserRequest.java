package com.api.porraf1.user.infrastructure.web.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequest {

    public record Register(
            @NotBlank
            String name,

            @NotBlank @Email
            String email,

            @NotBlank @Size(min = 8, message = "Password must be at least 8 characters")
            String password
    ) {}

}
