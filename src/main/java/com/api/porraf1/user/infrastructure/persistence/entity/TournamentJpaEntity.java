package com.api.porraf1.user.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tournaments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TournamentJpaEntity {
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToMany
    @JoinTable(
            name="users_tournaments",
            joinColumns = @JoinColumn(name="tournament_id"),
            inverseJoinColumns = @JoinColumn(name ="user_id")
    )
    private List<UserJpaEntity> userList;

    @Column(nullable = false, length = 10)
    private Integer season;

}
