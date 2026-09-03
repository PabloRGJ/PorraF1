package com.api.porraf1.user.infrastructure.persistence.repository.user;

import com.api.porraf1.user.infrastructure.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
    Optional<UserJpaEntity> findByEmail(String email);
    List<UserJpaEntity> findByActiveTrue();
    boolean existsByEmail(String email);
    List<UserJpaEntity> findByTournamentListId(UUID tournamentId);
}
