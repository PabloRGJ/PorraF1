package com.api.porraf1.user.infrastructure.persistence.repository.tournament;

import com.api.porraf1.user.infrastructure.persistence.entity.TournamentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TournamentJpaRepository extends JpaRepository<TournamentJpaEntity, UUID> {
    Optional<TournamentJpaEntity> findById(UUID id);
    List<TournamentJpaEntity> findAll();
    List<TournamentJpaEntity> findByUserListId(UUID userId);

}
