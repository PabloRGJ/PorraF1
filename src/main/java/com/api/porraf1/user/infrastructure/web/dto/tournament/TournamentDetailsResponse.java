package com.api.porraf1.user.infrastructure.web.dto.tournament;

import com.api.porraf1.user.application.port.in.query.tournament.TournamentDetailReadModel;
import java.util.List;
import java.util.UUID;

public record TournamentDetailsResponse(
        UUID tournamentId,
        String name,
        List<UserSummaryResponse> userList
) {

    public static TournamentDetailsResponse from(TournamentDetailReadModel tournamentDetail) {
        return new TournamentDetailsResponse(
                tournamentDetail.tournamentId(),
                tournamentDetail.name(),
                tournamentDetail.userList().stream().map(UserSummaryResponse::from).toList()
        );
    }

    public record UserSummaryResponse(
            UUID userId,
            String userName,
            int score
    ){
        public static UserSummaryResponse from(TournamentDetailReadModel.UserSummary userSummary){
            return new UserSummaryResponse(
                    userSummary.userId(),
                    userSummary.userName(),
                    userSummary.score()
            );
        }
    }
}

