package com.api.porraf1.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Event {
    private final UUID id;
    private String name;
    private Integer round;
    private Integer season;
    private EventType type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    public static Event create(String name, Integer round, Integer season, EventType type, LocalDateTime startTime, LocalDateTime endTime, List<Participation> participants){
        return new Event(
                UUID.randomUUID(),
                name,
                round,
                season,
                type,
                startTime,
                endTime
        );
    }

    public static Event reconstitute(UUID id, String name, Integer round, Integer season, EventType type, LocalDateTime startTime, LocalDateTime endTime) {
        return new Event(id,
                name,
                round,
                season,
                type,
                startTime,
                endTime
        );
    }

    public boolean equals(Event event){
        return EqualsBuilder.reflectionEquals(this,event);
    }


    public boolean hasBegun(){
        return LocalDateTime.now().isAfter(this.startTime);
    }
    public boolean isOngoing(){
        return LocalDateTime.now().isAfter(this.startTime) && LocalDateTime.now().isBefore(this.endTime);
    }
    public boolean hasEnded(){
        return LocalDateTime.now().isAfter(this.endTime);
    }
}
