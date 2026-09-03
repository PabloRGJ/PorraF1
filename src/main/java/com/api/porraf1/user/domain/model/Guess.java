package com.api.porraf1.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class Guess {
    private UUID id;
    private Participation participation;
    private String positionGuessed;

    public static Guess create(Participation participation,String positionGuessed){
        return new Guess(
                UUID.randomUUID(),
                participation,
                positionGuessed
        );
    }
    public static Guess reconstitute(UUID id, Participation participation,String positionGuessed){
        return new Guess(
                id,
                participation,
                positionGuessed
        );
    }

    public boolean isCorrect(){
        return (participation.isUpdated() && positionGuessed.equals(participation.getFinalPosition()));
    }

    public Event getEvent(){
        return this.getParticipation().getEvent();
    }

}
