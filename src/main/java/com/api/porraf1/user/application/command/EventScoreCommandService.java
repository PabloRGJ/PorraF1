package com.api.porraf1.user.application.command;

import com.api.porraf1.user.application.port.in.command.eventscore.UpdateIfNeededTournamentScoresCommand;
import com.api.porraf1.user.application.port.out.EventScoreRepositoryPort;
import com.api.porraf1.user.application.port.out.ParticipationRepositoryPort;
import com.api.porraf1.user.application.port.out.TournamentRepositoryPort;
import com.api.porraf1.user.domain.exception.ResourceNotFoundException;
import com.api.porraf1.user.domain.model.EventScore;
import com.api.porraf1.user.domain.model.Participation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class EventScoreCommandService {

    private final TournamentRepositoryPort tournamentRepository;
    private final EventScoreRepositoryPort eventScoreRepository;
    private final ParticipationRepositoryPort participationRepository;


    public void handle(UpdateIfNeededTournamentScoresCommand command) {
        tournamentRepository.findById(command.tournamentId()).
                orElseThrow(() -> new ResourceNotFoundException(command.tournamentId())); //Comprobar que existe torneo, redundante
        List<EventScore> eventScores= eventScoreRepository.findByTournamentId(command.tournamentId());
        eventScores.stream().filter(eventScore -> eventScore.getScore()==null).forEach(eventScore -> {
            List<Participation> participationList = participationRepository.findByEventId(eventScore.getEvent().getId());
            eventScore.calculateAndSetScore(participationList);
            eventScoreRepository.save(eventScore);
        });

    }
}
