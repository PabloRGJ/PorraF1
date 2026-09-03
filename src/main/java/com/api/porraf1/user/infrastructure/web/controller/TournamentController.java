package com.api.porraf1.user.infrastructure.web.controller;

import com.api.porraf1.shared.infrastructure.web.ApiResponse;
import com.api.porraf1.user.application.command.EventScoreCommandService;
import com.api.porraf1.user.application.port.in.command.eventscore.UpdateIfNeededTournamentScoresCommand;
import com.api.porraf1.user.application.port.in.query.tournament.GetTournamentDetailsQuery;
import com.api.porraf1.user.application.port.in.query.tournament.TournamentDetailReadModel;
import com.api.porraf1.user.application.query.TournamentQueryService;
import com.api.porraf1.user.application.port.in.query.tournament.GetAllTournamentsQuery;
import com.api.porraf1.user.infrastructure.web.dto.tournament.TournamentDetailsResponse;
import com.api.porraf1.user.infrastructure.web.dto.tournament.TournamentResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tournaments")
@AllArgsConstructor
public class TournamentController {
   // private final TournamentCommandService commandService;
    private final TournamentQueryService queryService;
    private final EventScoreCommandService eventScoreCommandService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TournamentResponse>>> getAll() {
        List<TournamentResponse> tournaments = queryService.handle(new GetAllTournamentsQuery())
                .stream()
                .map(TournamentResponse::from)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(tournaments, "Tournaments retrieved succesfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TournamentDetailsResponse>> getTournamentDetails(@PathVariable UUID id){
        eventScoreCommandService.handle(new UpdateIfNeededTournamentScoresCommand(id)); //Cuando obtenemos los resultados del torneo recalculamos el score
        TournamentDetailReadModel tournament = queryService.handle(new GetTournamentDetailsQuery(id));
        return ResponseEntity.ok(ApiResponse.success(TournamentDetailsResponse.from(tournament),"Tournament details retrieved succesfully"));
    }

}
