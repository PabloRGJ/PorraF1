package com.api.porraf1.user.application.port.in.command.eventscore;

import java.util.UUID;

public record UpdateIfNeededTournamentScoresCommand(
        UUID tournamentId
) {
    public UpdateIfNeededTournamentScoresCommand {
        if (tournamentId == null) throw new IllegalArgumentException("tournamentId is required");
    }
}
