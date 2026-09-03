package com.api.porraf1.user.infrastructure.persistence.entity;

import com.api.porraf1.user.domain.model.EventType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventJpaEntity {

    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;
    @Column(nullable = false, length = 200)
    private String name;
    @Column(nullable = false, length = 10)
    private Integer round;
    @Column(nullable = false, length = 10)
    private Integer season;
    @Column(nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private EventType type;
    @Column
    private LocalDateTime startTime;
    @Column
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "event")
    private List<ParticipationJpaEntity> participationList;
}
