package com.api.porraf1.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Pilot {
    private UUID id;
    private String name;
    private String nick;
    private String team;

    public static Pilot create(String name, String nick, String team){
        return new Pilot(
                UUID.randomUUID(),
                name,
                nick,
                team
        );
    }
    public static Pilot reconstitute(UUID id,String name, String nick, String team){
        return new Pilot(id, name, nick, team);
    }


}
