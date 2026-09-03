package com.api.porraf1.user.infrastructure.persistence.repository.event;

import com.api.porraf1.user.infrastructure.persistence.entity.EventJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EventJpaRepository extends JpaRepository<EventJpaEntity, UUID> {
    List<EventJpaEntity> findBySeason(int season);
}
