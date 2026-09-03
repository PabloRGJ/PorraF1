package com.api.porraf1.user.infrastructure.persistence.mapper;

import com.api.porraf1.user.domain.model.Participation;
import com.api.porraf1.user.infrastructure.persistence.entity.ParticipationJpaEntity;


public class ParticipationMapper {
    public static ParticipationJpaEntity toJpaEntity(Participation participation){
        return ParticipationJpaEntity.builder()
                .id(participation.getId())
                .pilot(PilotMapper.toJpaEntity(participation.getPilot()))
                .event(EventMapper.toJpaEntity(participation.getEvent()))
                .finalPosition(participation.getFinalPosition())
                .build();
    }
    public static Participation toDomain(ParticipationJpaEntity entity){
        return Participation.reconstitute(
                entity.getId(),
                PilotMapper.toDomain(entity.getPilot()),
                EventMapper.toDomain(entity.getEvent()),
                entity.getFinalPosition()
        );
    }
}
