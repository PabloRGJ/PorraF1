package com.api.porraf1.user.infrastructure.persistence.repository.user;

import com.api.porraf1.user.domain.model.User;
import com.api.porraf1.user.application.port.out.UserRepositoryPort;
import com.api.porraf1.user.infrastructure.persistence.entity.UserJpaEntity;
import com.api.porraf1.user.infrastructure.persistence.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Adaptador — implementa el puerto de salida usando Spring Data JPA.
 * Es la única clase que conoce tanto el dominio como la infraestructura.
 */
@Component
@AllArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository jpaRepository;

    @Override
    public User save(User user) {
        UserJpaEntity saved = jpaRepository.save(UserMapper.toJpaEntity(user));
        return UserMapper.toDomain(saved);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id).map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(UserMapper::toDomain);
    }

    @Override
    public List<User> findAllActive() {
        return jpaRepository.findByActiveTrue()
                .stream()
                .map(UserMapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<User> findByTournamentId(UUID tournamentId){
        return jpaRepository.findByTournamentListId(tournamentId).stream().map(UserMapper::toDomain).toList();
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }
}
