package com.api.porraf1.user.infrastructure.persistence.entity;

import com.api.porraf1.user.domain.model.Participation;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "guesses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuessJpaEntity {

    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participation_id")
    private ParticipationJpaEntity participation;

    @Column(nullable = false, length = 3)
    private String positionGuessed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_scores_id")
    private EventScoreJpaEntity eventScore;
}
