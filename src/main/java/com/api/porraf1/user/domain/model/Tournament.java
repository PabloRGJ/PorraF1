package com.api.porraf1.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Tournament {
    private final UUID id;
    private String name;
    private List<User> userList;
    private Integer season;


    public static Tournament create(String name, User user, Integer season){
        return new Tournament(
                UUID.randomUUID(),
                name,
                List.of(user),
                season
        );
    }

    public static Tournament reconstitute(UUID id, String name, List<User> userList,Integer season) {
        return new Tournament(id, name, userList, season);
    }
}
