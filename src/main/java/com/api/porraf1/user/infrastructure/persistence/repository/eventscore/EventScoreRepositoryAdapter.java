package com.api.porraf1.user.infrastructure.persistence.repository.eventscore;

import com.api.porraf1.user.application.port.out.EventScoreRepositoryPort;
import com.api.porraf1.user.domain.model.EventScore;
import com.api.porraf1.user.infrastructure.persistence.mapper.EventScoreMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class EventScoreRepositoryAdapter implements EventScoreRepositoryPort {
    private EventScoreJpaRepository eventScoreJpaRepository;
    @Override
    public List<EventScore> findByTournamentId(UUID tournamentId) {
        return eventScoreJpaRepository.findByTournamentId(tournamentId).stream().map(EventScoreMapper::toDomain).toList();
    }

    @Override
    public List<EventScore> findByUserId(UUID userId) {
        return eventScoreJpaRepository.findByUserId(userId).stream().map(EventScoreMapper::toDomain).toList();
    }

    @Override
    public EventScore save(EventScore save) {
        return null;
    }
}
