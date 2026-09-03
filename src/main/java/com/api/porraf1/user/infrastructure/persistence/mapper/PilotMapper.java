package com.api.porraf1.user.infrastructure.persistence.mapper;

import com.api.porraf1.user.domain.model.Pilot;
import com.api.porraf1.user.infrastructure.persistence.entity.PilotJpaEntity;

public class PilotMapper {
    public static PilotJpaEntity toJpaEntity(Pilot pilot){
        return PilotJpaEntity.builder()
                .id(pilot.getId())
                .name(pilot.getName())
                .nick(pilot.getNick())
                .team(pilot.getTeam())
                .build();
    }

    public static Pilot toDomain(PilotJpaEntity entity){
        return Pilot.reconstitute(
                entity.getId(),
                entity.getName(),
                entity.getNick(),
                entity.getTeam()
        );
    }
}
