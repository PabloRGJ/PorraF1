package com.api.porraf1.user.application.port.out;

import com.api.porraf1.user.domain.model.Participation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParticipationRepositoryPort {
    Optional<Participation> findById(UUID id);
    List<Participation> findByEventId(UUID eventId);
}
