package com.api.porraf1.user.infrastructure.persistence.repository.event;

import com.api.porraf1.user.application.port.out.EventRepositoryPort;
import com.api.porraf1.user.domain.model.Event;
import com.api.porraf1.user.infrastructure.persistence.mapper.EventMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class EventRepositoryAdapter implements EventRepositoryPort {
    private EventJpaRepository jpaRepository;
    @Override
    public Optional<Event> findById(UUID eventId) {
        return jpaRepository.findById(eventId).map(EventMapper::toDomain);
    }

    @Override
    public List<Event> findBySeason(int season) {
        return jpaRepository.findBySeason(season).stream().map(EventMapper::toDomain).toList();
    }
}
