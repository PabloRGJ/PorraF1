package com.api.porraf1.user.infrastructure.web.dto.tournament;

import com.api.porraf1.user.domain.model.Tournament;
import com.api.porraf1.user.infrastructure.web.dto.user.UserResponse;

import java.util.List;
import java.util.UUID;

public record TournamentResponse(
    UUID id,
    String name,
    List<UserResponse> userList
) {
        public static TournamentResponse from(Tournament user) {
            return new TournamentResponse(
                    user.getId(),
                    user.getName(),
                    user.getUserList().stream().map(UserResponse::from).toList()
            );
        }
}
