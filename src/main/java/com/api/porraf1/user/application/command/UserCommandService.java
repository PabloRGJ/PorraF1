package com.api.porraf1.user.application.command;

import com.api.porraf1.user.domain.exception.UserAlreadyExistsException;
import com.api.porraf1.user.domain.exception.UserNotFoundException;
import com.api.porraf1.user.domain.model.User;
import com.api.porraf1.user.application.port.in.command.user.ChangeUserNameCommand;
import com.api.porraf1.user.application.port.in.command.user.DeactivateUserCommand;
import com.api.porraf1.user.application.port.in.command.user.RegisterUserCommand;
import com.api.porraf1.user.application.port.out.PasswordEncoderPort;
import com.api.porraf1.user.application.port.out.UserRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Orquesta commands — solo escribe, nunca devuelve datos de lectura.
 * Depende únicamente de puertos (interfaces), nunca de infraestructura directamente.
 */
@Service
@Transactional
public class UserCommandService {

    private final UserRepositoryPort userRepository;
    private final PasswordEncoderPort passwordEncoder;

    public UserCommandService(UserRepositoryPort userRepository,
                              PasswordEncoderPort passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User handle(RegisterUserCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new UserAlreadyExistsException(command.email());
        }

        String hash = passwordEncoder.encode(command.password());
        User user = User.create(command.name(), command.email(), hash);

        return userRepository.save(user);
    }

    public void handle(DeactivateUserCommand command) {
        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new UserNotFoundException(command.userId()));

        user.deactivate();     // lógica en el dominio, no aquí
        userRepository.save(user);
    }

    public void handle(ChangeUserNameCommand command){
        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new UserNotFoundException(command.userId()));

        user.changeUserName(command.userName());
        userRepository.save(user);
    }
}
