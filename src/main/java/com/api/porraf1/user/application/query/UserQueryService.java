package com.api.porraf1.user.application.query;

import com.api.porraf1.user.domain.exception.UserNotFoundException;
import com.api.porraf1.user.domain.model.Tournament;
import com.api.porraf1.user.domain.model.User;
import com.api.porraf1.user.application.port.in.query.user.GetAllUsersQuery;
import com.api.porraf1.user.application.port.in.query.user.GetTournamentsByUserIdQuery;
import com.api.porraf1.user.application.port.in.query.user.GetUserByIdQuery;
import com.api.porraf1.user.application.port.out.TournamentRepositoryPort;
import com.api.porraf1.user.application.port.out.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Orquesta queries — solo lee, nunca modifica estado.
 * readOnly=true optimiza la transacción y deja claro la intención.
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class UserQueryService {

    private final UserRepositoryPort userRepository;
    private final TournamentRepositoryPort tournamentRepository;

    public User handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId())
                .orElseThrow(() -> new UserNotFoundException(query.userId()));
    }

    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAllActive();
    }

    public List<Tournament> handle (GetTournamentsByUserIdQuery query){
         userRepository.findById(query.userId())
                .orElseThrow(() -> new UserNotFoundException(query.userId()));

         return tournamentRepository.findByUserId(query.userId());
    }
}
