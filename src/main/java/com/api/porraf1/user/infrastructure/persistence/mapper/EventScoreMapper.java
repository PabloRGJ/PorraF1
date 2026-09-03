package com.api.porraf1.user.infrastructure.persistence.mapper;

import com.api.porraf1.user.domain.model.EventScore;
import com.api.porraf1.user.infrastructure.persistence.entity.EventScoreJpaEntity;


public class EventScoreMapper {
    public static EventScoreJpaEntity toJpaEntity(EventScore domain) {
        return EventScoreJpaEntity.builder()
                .id(domain.getId())
                .event(EventMapper.toJpaEntity(domain.getEvent()))
                .build();
    }

    public static EventScore toDomain(EventScoreJpaEntity entity) {
        return EventScore.reconstitute(
                entity.getId(),
                UserMapper.toDomain(entity.getUser()),
                TournamentMapper.toDomain(entity.getTournament()),
                EventMapper.toDomain(entity.getEvent()),
                entity.getGuessList().stream().map(GuessMapper::toDomain).toList(),
                entity.getScore()
        );
    }
}
