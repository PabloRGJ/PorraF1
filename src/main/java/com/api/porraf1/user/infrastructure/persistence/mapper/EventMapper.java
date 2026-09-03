package com.api.porraf1.user.infrastructure.persistence.mapper;

import com.api.porraf1.user.domain.model.Event;
import com.api.porraf1.user.infrastructure.persistence.entity.EventJpaEntity;

public class EventMapper {
    public static EventJpaEntity toJpaEntity(Event event) {
        return EventJpaEntity.builder()
                .id(event.getId())
                .name(event.getName())
                .round(event.getRound())
                .season(event.getSeason())
                .type(event.getType())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .build();
    }

    public static Event toDomain(EventJpaEntity entity) {
        return Event.reconstitute(
                entity.getId(),
                entity.getName(),
                entity.getRound(),
                entity.getSeason(),
                entity.getType(),
                entity.getStartTime(),
                entity.getEndTime()
        );
    }
}
