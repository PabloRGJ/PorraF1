package com.api.porraf1.user.application.port.out;

import com.api.porraf1.user.domain.model.EventScore;
import com.api.porraf1.user.domain.model.Tournament;

import java.util.List;
import java.util.UUID;

public interface EventScoreRepositoryPort {
    List<EventScore> findByTournamentId(UUID tournamentId);
    List<EventScore> findByUserId(UUID userId);
    EventScore save(EventScore save);
}
