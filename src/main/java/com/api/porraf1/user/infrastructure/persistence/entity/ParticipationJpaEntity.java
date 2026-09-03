package com.api.porraf1.user.infrastructure.persistence.entity;

import com.api.porraf1.user.domain.model.Event;
import com.api.porraf1.user.domain.model.Pilot;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "participations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipationJpaEntity {
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pilot_id")
    private PilotJpaEntity pilot;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="event_id")
    private EventJpaEntity event;
    @Column(nullable = false, length = 3)
    private String finalPosition;
}
