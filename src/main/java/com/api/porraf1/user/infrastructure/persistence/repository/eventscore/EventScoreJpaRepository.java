package com.api.porraf1.user.infrastructure.persistence.repository.eventscore;

import com.api.porraf1.user.infrastructure.persistence.entity.EventScoreJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventScoreJpaRepository extends JpaRepository<EventScoreJpaEntity, UUID> {
    List<EventScoreJpaEntity> findByTournamentId(UUID id);
    List<EventScoreJpaEntity> findByUserId(UUID id);
}
