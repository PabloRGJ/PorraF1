package com.api.porraf1.user.infrastructure.persistence.repository.participation;

import com.api.porraf1.user.infrastructure.persistence.entity.ParticipationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ParticipationJpaRepository extends JpaRepository<ParticipationJpaEntity, UUID> {
    List<ParticipationJpaEntity> findByEventId(UUID eventId);
}
