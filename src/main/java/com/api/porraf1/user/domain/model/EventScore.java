package com.api.porraf1.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class EventScore {
    private UUID id ;
    private User user;
    private Tournament tournament;
    private Event event;
    private List<Guess> guessList;
    private Integer score;

    private static final int RACE_GUESSES_NUMBER=5;
    private static final int SPRINT_RACE_GUESSES_NUMBER=3;
    private static final int QUALY_GUESSES_NUMBER=5;
    private static final int SPRINT_QUALY_GUESSES_NUMBER=3;

    public static EventScore create(User user, Tournament tournament, Event event, List<Guess> guessList){
        if(checkGuessesSameEvent(event, guessList)) throw new IllegalArgumentException("Guesses from the list must be from the same event as the event provided");
        return new EventScore(
                UUID.randomUUID(),
                user,
                tournament,
                event,
                guessList,
                null
        );
    }
    public static EventScore reconstitute(UUID id, User user, Tournament tournament, Event event, List<Guess> guessList, Integer score){
        if(!checkGuessesSameEvent(event, guessList)) throw new IllegalArgumentException("Guesses from the list must be from the same event as the event provided");
        if(!checkGuessValidityNumber(event, guessList))  throw new IllegalArgumentException("Incorrect number of guesses in this event");
        return new EventScore(
                id,
                user,
                tournament,
                event,
                guessList,
                score
        );
    }

    private static boolean checkGuessValidityNumber(Event event, List<Guess> guessList) {
        return switch (event.getType()){
            case SPRINT_QUALY -> guessList.size() == SPRINT_QUALY_GUESSES_NUMBER;
            case SPRINT -> guessList.size()==SPRINT_RACE_GUESSES_NUMBER;
            case RACE_QUALY -> guessList.size()==QUALY_GUESSES_NUMBER;
            case RACE -> guessList.size()==RACE_GUESSES_NUMBER;
        };
    }

    private static boolean checkGuessesSameEvent(Event event, List<Guess> guessList){
        return guessList.stream().allMatch(guess -> guess.getEvent().equals(event));
    }

    //public static Integer calculateTotalPoints(List<Guess> eventGuesses){
      //  Map<Event, List<Guess>> organisedEventGuesses= eventGuesses.stream().collect(Collectors.groupingBy(Guess::getEvent));
        //return organisedEventGuesses.values().stream().reduce(0,(totalPoints, guessList) -> totalPoints + calculateAndSetScore(guessList), Integer::sum);
    //}

    public Integer calculateAndSetScore(List<Participation> eventResults){
        this.score =
                switch (this.event.getType()){
            case RACE ->  calculateRaceScore(eventResults);
            case RACE_QUALY ->  calculateRaceQualyScore(eventResults);
            case SPRINT -> calculateSprintScore(eventResults);
            case SPRINT_QUALY -> calculateSprintQualyScore(eventResults);
        };
        return this.score;
    }

    private Integer calculateRaceScore(List<Participation> eventResults) {
        List<Pilot> topThree= eventResults.stream().filter(participation ->
                participation.isUpdated() &&
                        (participation.getFinalPosition().equals("1º") ||
                                participation.getFinalPosition().equals("2º") ||
                                participation.getFinalPosition().equals("3º"))
        ).map(Participation::getPilot).toList();
        List<Pilot> topFive= eventResults.stream().filter(participation ->
                participation.isUpdated() &&
                        (participation.getFinalPosition().equals("1º") ||
                                participation.getFinalPosition().equals("2º") ||
                                participation.getFinalPosition().equals("3º") ||
                                participation.getFinalPosition().equals("4º") ||
                                participation.getFinalPosition().equals("5º") )
        ).map(Participation::getPilot).toList();
        int totalPoints = (int) guessList.stream().filter(guess -> topFive.contains(guess.getParticipation().getPilot())).count(); //1 punto por cada piloto en el top 5 desordenado
        if(guessList.stream().allMatch(Guess::isCorrect)) totalPoints=totalPoints+5; //5 puntos extras por adivinar el top 5 ordenado
        List<Guess> topThreeGuessList = guessList.stream().filter(guess ->
                guess.getPositionGuessed().equals("1º") ||
                guess.getPositionGuessed().equals("2º") ||
                guess.getPositionGuessed().equals("3º")
        ).toList();
        if(topThreeGuessList.stream().allMatch(Guess::isCorrect)) totalPoints= totalPoints+3;//3 puntos extras por adivinar el top 3 ordenado
        return totalPoints;
    }

    private Integer calculateRaceQualyScore(List<Participation> eventResults) {
        int totalPoints= (int) guessList.stream().filter(Guess::isCorrect).count(); //1 punto por posicion correcta adivinada
        List<Pilot> topThree= eventResults.stream().filter(participation ->
                participation.isUpdated() &&
                        (participation.getFinalPosition().equals("1º") ||
                                participation.getFinalPosition().equals("2º") ||
                                participation.getFinalPosition().equals("3º"))
        ).map(Participation::getPilot).toList();
        List<Pilot> topFive= eventResults.stream().filter(participation ->
                participation.isUpdated() &&
                        (participation.getFinalPosition().equals("1º") ||
                                participation.getFinalPosition().equals("2º") ||
                                participation.getFinalPosition().equals("3º") ||
                                participation.getFinalPosition().equals("4º") ||
                                participation.getFinalPosition().equals("5º") )
        ).map(Participation::getPilot).toList();
        if(guessList.stream().allMatch(guess -> topThree.contains(guess.getParticipation().getPilot()))) totalPoints++; //Punto extra por adivinar todos los pilotos en top tres sin ordenar
        if(guessList.stream().allMatch(guess -> topFive.contains(guess.getParticipation().getPilot()))) totalPoints=totalPoints+2; //Punto extra por adivinar todos los pilotos en top cinco sin ordenar
        return totalPoints;
    }

    private Integer calculateSprintQualyScore(List<Participation> eventResults){
        int totalPoints= (int) guessList.stream().filter(Guess::isCorrect).count(); //1 punto por posicion correcta adivinada
        List<Pilot> topThree= eventResults.stream().filter(participation ->
                participation.isUpdated() &&
                        (participation.getFinalPosition().equals("1º") ||
                        participation.getFinalPosition().equals("2º") ||
                        participation.getFinalPosition().equals("3º"))
        ).map(Participation::getPilot).toList();
        if(guessList.stream().allMatch(guess -> topThree.contains(guess.getParticipation().getPilot()))) totalPoints++; //Punto extra por adivinar todos los pilotos en top tres sin ordenar
        return totalPoints;
    }

    private Integer calculateSprintScore(List<Participation> eventResults){
        int totalPoints= (int) guessList.stream().filter(Guess::isCorrect).count(); //1 punto por posicion correcta adivinada
        List<Pilot> topThree= eventResults.stream().filter(participation ->
                participation.isUpdated() &&
                        (participation.getFinalPosition().equals("1º") ||
                                participation.getFinalPosition().equals("2º") ||
                                participation.getFinalPosition().equals("3º"))
        ).map(Participation::getPilot).toList();
        totalPoints += (int) guessList.stream().filter(guess -> topThree.contains(guess.getParticipation().getPilot())).count(); //1 punto por cada piloto en el top 3 desordenado
        return totalPoints;
    }

}
