package com.api.porraf1.user.application.port.in.query.tournament;

import com.api.porraf1.user.domain.model.Event;
import com.api.porraf1.user.domain.model.User;

import java.util.List;
import java.util.UUID;

public record TournamentDetailReadModel(
        UUID tournamentId,
        String name,
        List<UserSummary> userList
) {

    public record UserSummary(
            UUID userId,
            String userName,
            int score
    ){}
}
