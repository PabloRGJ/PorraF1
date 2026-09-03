package com.api.porraf1.user.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pilots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PilotJpaEntity {
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, length = 3)
    private String nick;
    @Column(nullable = false, length = 50)
    private String team;

    @OneToMany(mappedBy = "pilot")
    private List<ParticipationJpaEntity> participationList;
}
