package com.api.porraf1.user.application.query;

import com.api.porraf1.user.application.port.in.query.tournament.GetTournamentDetailsQuery;
import com.api.porraf1.user.application.port.in.query.tournament.TournamentDetailReadModel;
import com.api.porraf1.user.application.port.out.EventRepositoryPort;
import com.api.porraf1.user.application.port.out.EventScoreRepositoryPort;
import com.api.porraf1.user.application.port.out.UserRepositoryPort;
import com.api.porraf1.user.domain.exception.ResourceNotFoundException;
import com.api.porraf1.user.domain.model.EventScore;
import com.api.porraf1.user.domain.model.Tournament;
import com.api.porraf1.user.application.port.in.query.tournament.GetAllTournamentsQuery;
import com.api.porraf1.user.application.port.in.query.tournament.GetTournamentByIdQuery;
import com.api.porraf1.user.application.port.out.TournamentRepositoryPort;
import com.api.porraf1.user.domain.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class TournamentQueryService {

    private final TournamentRepositoryPort tournamentRepository;
    private final UserRepositoryPort userRepository;
    private final EventScoreRepositoryPort eventScoreRepository;
    private final EventRepositoryPort eventRepository;


    public Tournament handle(GetTournamentByIdQuery query) {
        return tournamentRepository.findById(query.tournamentId())
                .orElseThrow(() -> new ResourceNotFoundException(query.tournamentId()));
    }

    public List<Tournament> handle(GetAllTournamentsQuery query) {
        return tournamentRepository.findAll();
    }

    public TournamentDetailReadModel handle(GetTournamentDetailsQuery query) {
        Tournament tournament = tournamentRepository.findById(query.tournamentId())
                .orElseThrow(() -> new ResourceNotFoundException(query.tournamentId()));
        List<User> userList = userRepository.findByTournamentId(tournament.getId());//Obtenemos usuarios
        List<TournamentDetailReadModel.UserSummary> userSummaryList = new ArrayList<>();
        userList.forEach(user ->{
            List<EventScore> eventScores= eventScoreRepository.findByUserId(user.getId());
            int userScore=eventScores.stream().mapToInt(EventScore::getScore).sum(); //Obtenemos el score de cada usuario
            userSummaryList.add(
                    new TournamentDetailReadModel.UserSummary(
                            user.getId(),
                            user.getUserName(),
                            userScore
                    )
            );
            userSummaryList.sort(Comparator.comparingInt(TournamentDetailReadModel.UserSummary::score).reversed());
        });

        return new TournamentDetailReadModel(tournament.getId(),tournament.getName(),userSummaryList);


    }
}
