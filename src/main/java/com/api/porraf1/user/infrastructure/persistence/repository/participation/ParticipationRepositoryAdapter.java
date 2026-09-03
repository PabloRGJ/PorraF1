package com.api.porraf1.user.infrastructure.persistence.repository.participation;

import com.api.porraf1.user.application.port.out.ParticipationRepositoryPort;
import com.api.porraf1.user.domain.model.Participation;
import com.api.porraf1.user.infrastructure.persistence.mapper.ParticipationMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ParticipationRepositoryAdapter implements ParticipationRepositoryPort {
    private ParticipationJpaRepository jpaRepository;
    @Override
    public Optional<Participation> findById(UUID id) {
        return jpaRepository.findById(id).map(ParticipationMapper::toDomain);
    }

    @Override
    public List<Participation> findByEventId(UUID eventId) {
        return jpaRepository.findByEventId(eventId).stream().map(ParticipationMapper::toDomain).toList();
    }
}
