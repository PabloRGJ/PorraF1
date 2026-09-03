package com.api.porraf1.user.infrastructure.persistence.repository.tournament;

import com.api.porraf1.user.domain.model.Tournament;
import com.api.porraf1.user.application.port.out.TournamentRepositoryPort;
import com.api.porraf1.user.infrastructure.persistence.entity.TournamentJpaEntity;
import com.api.porraf1.user.infrastructure.persistence.mapper.TournamentMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class TournamentRepositoryAdapter implements TournamentRepositoryPort {

    private TournamentJpaRepository tournamentJpaRepository;

    @Override
    public Tournament save(Tournament tournament) {
        TournamentJpaEntity entity= tournamentJpaRepository.save(TournamentMapper.toJpaEntity(tournament));
        return TournamentMapper.toDomain(entity);
    }

    @Override
    public List<Tournament> findAll() {
        return tournamentJpaRepository.findAll().stream().map(TournamentMapper::toDomain).toList();
    }

    @Override
    public Optional<Tournament> findById(UUID id) {
        return TournamentMapper.toDomain(tournamentJpaRepository.findById(id));
    }

    @Override
    public List<Tournament> findByUserId(UUID userId) {
        return tournamentJpaRepository.findByUserListId(userId).stream().map(TournamentMapper::toDomain).toList();
    }
}
