package com.api.porraf1.user.infrastructure.persistence.entity;

import com.api.porraf1.user.domain.model.Event;
import com.api.porraf1.user.domain.model.Guess;
import com.api.porraf1.user.domain.model.Tournament;
import com.api.porraf1.user.domain.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "event_scores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventScoreJpaEntity {

    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserJpaEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    private TournamentJpaEntity tournament;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private EventJpaEntity event;

    @OneToMany(mappedBy = "eventScore")
    private List<GuessJpaEntity> guessList;

    @Column
    private Integer score;
}
