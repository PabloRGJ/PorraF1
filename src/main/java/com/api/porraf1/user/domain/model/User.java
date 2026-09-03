package com.api.porraf1.user.domain.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class User {

    private final UUID id;
    private String userName;
    private String email;
    private String passwordHash;
    private boolean active;

    private User(UUID id, String userName, String email, String passwordHash,
                 boolean active) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.active = active;
    }

    public static User create(String name, String email, String passwordHash) {
        return new User(
                UUID.randomUUID(),
                name,
                email,
                passwordHash,
                true
        );
    }

    public static User reconstitute(UUID id, String name, String email, String passwordHash,
                                     boolean active) {
        return new User(id, name, email, passwordHash, active);
    }

    public void deactivate() {
        if (!this.active) {
            throw new IllegalStateException("User is already inactive");
        }
        this.active = false;
    }

    public void changeUserName(String userName){
        this.userName=userName;
    }

}
