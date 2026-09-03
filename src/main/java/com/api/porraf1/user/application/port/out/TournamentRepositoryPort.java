package com.api.porraf1.user.application.port.out;

import com.api.porraf1.user.domain.model.Tournament;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TournamentRepositoryPort {
    Tournament save(Tournament tournament);
    List<Tournament> findAll();
    Optional<Tournament> findById(UUID id);
    List<Tournament> findByUserId(UUID userId);
}
