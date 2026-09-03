package com.api.porraf1.user.application.port.out;

import com.api.porraf1.user.domain.model.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepositoryPort {
    Optional<Event> findById(UUID eventId);
    List<Event> findBySeason(int season);
}
