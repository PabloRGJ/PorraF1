package com.api.porraf1.user.infrastructure.persistence.mapper;

import com.api.porraf1.user.domain.model.Tournament;
import com.api.porraf1.user.infrastructure.persistence.entity.TournamentJpaEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
@AllArgsConstructor
public class TournamentMapper {
    public static TournamentJpaEntity toJpaEntity(Tournament tournament) {
        return TournamentJpaEntity.builder()
                .id(tournament.getId())
                .name(tournament.getName())
                .userList(tournament.getUserList().stream()
                        .map(UserMapper::toJpaEntity)
                        .toList())
                .season(tournament.getSeason())
                .build();
    }

    public static Tournament toDomain(TournamentJpaEntity entity) {
        return Tournament.reconstitute(
                entity.getId(),
                entity.getName(),
                entity.getUserList().stream().map(UserMapper::toDomain).toList(),
                entity.getSeason()
        );
    }

    public static Optional<Tournament> toDomain(Optional<TournamentJpaEntity> entity) {
        return entity.map(tournamentJpaEntity -> Tournament.reconstitute(
                tournamentJpaEntity.getId(),
                tournamentJpaEntity.getName(),
                tournamentJpaEntity.getUserList().stream().map(UserMapper::toDomain).toList(),
                tournamentJpaEntity.getSeason()
        ));
    }
}
