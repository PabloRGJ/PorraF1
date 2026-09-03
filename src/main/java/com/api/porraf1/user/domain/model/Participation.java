package com.api.porraf1.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Participation {
    private UUID id;
    private Pilot pilot;
    private Event event;
    private String finalPosition;

    public static Participation create(Pilot pilot,  Event event){
        return new Participation(
                UUID.randomUUID(),
                pilot,
                event,
                null
        );
    }

    public static Participation reconstitute(UUID id, Pilot pilot,  Event event,String finalPosition){
        return new Participation(id, pilot, event, finalPosition);
    }

    public boolean isUpdated(){
        return this.event.hasEnded() && this.finalPosition!=null;
    }
}
