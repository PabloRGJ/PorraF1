package com.api.porraf1.user.application.port.in.query.tournament;

import java.util.UUID;

public record GetTournamentDetailsQuery(
        UUID tournamentId
) {
}
