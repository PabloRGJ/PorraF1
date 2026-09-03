package com.api.porraf1.user.application.port.out;

import com.api.porraf1.user.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de salida — la plicacion define QUÉ necesita,
 * la infraestructura decide CÓMO lo implementa.
 * La aplicacion no sabe si hay PostgreSQL, MongoDB o un Map en memoria.
 */
public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
    List<User> findAllActive();
    boolean existsByEmail(String email);
    List<User> findByTournamentId(UUID tournamentId);
}
