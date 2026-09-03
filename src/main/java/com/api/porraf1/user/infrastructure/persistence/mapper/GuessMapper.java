package com.api.porraf1.user.infrastructure.persistence.mapper;

import com.api.porraf1.user.domain.model.Guess;
import com.api.porraf1.user.infrastructure.persistence.entity.GuessJpaEntity;

public class GuessMapper {
    public static GuessJpaEntity toJpaEntity(Guess guess){
        return GuessJpaEntity.builder()
                .id(guess.getId())
                .participation(ParticipationMapper.toJpaEntity(guess.getParticipation()))
                .positionGuessed(guess.getPositionGuessed())
                .build();
    }

    public static Guess toDomain(GuessJpaEntity entity){
        return Guess.reconstitute(
                entity.getId(),
                ParticipationMapper.toDomain(entity.getParticipation()),
                entity.getPositionGuessed()
        );
    }
}
