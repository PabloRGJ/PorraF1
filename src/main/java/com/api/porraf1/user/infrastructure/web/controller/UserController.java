package com.api.porraf1.user.infrastructure.web.controller;

import com.api.porraf1.shared.infrastructure.web.ApiResponse;
import com.api.porraf1.user.application.command.UserCommandService;
import com.api.porraf1.user.application.query.UserQueryService;
import com.api.porraf1.user.domain.model.User;
import com.api.porraf1.user.application.port.in.command.user.ChangeUserNameCommand;
import com.api.porraf1.user.application.port.in.command.user.DeactivateUserCommand;
import com.api.porraf1.user.application.port.in.command.user.RegisterUserCommand;
import com.api.porraf1.user.application.port.in.query.user.GetAllUsersQuery;
import com.api.porraf1.user.application.port.in.query.user.GetTournamentsByUserIdQuery;
import com.api.porraf1.user.application.port.in.query.user.GetUserByIdQuery;
import com.api.porraf1.user.infrastructure.web.dto.tournament.TournamentResponse;
import com.api.porraf1.user.infrastructure.web.dto.user.UserRequest;
import com.api.porraf1.user.infrastructure.web.dto.user.UserResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Adaptador de entrada web.
 * Traduce HTTP → Commands/Queries → HTTP.
 * No contiene lógica de negocio.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserController(UserCommandService commandService, UserQueryService queryService) {
        this.userCommandService = commandService;
        this.userQueryService = queryService;
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAll() {
        List<UserResponse> users = userQueryService.handle(new GetAllUsersQuery())
                .stream()
                .map(UserResponse::from)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(users, "Users retrieved succesfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getById(@PathVariable UUID id) {
        User user = userQueryService.handle(new GetUserByIdQuery(id));
        return ResponseEntity.ok(ApiResponse.success(UserResponse.from(user), "User found"));
    }


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(
            @Valid @RequestBody UserRequest.Register request) {

        User user = userCommandService.handle(
                new RegisterUserCommand(request.name(), request.email(), request.password())
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(UserResponse.from(user), "User registered"));
    }


    @PutMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<Void>> deactivate(@PathVariable UUID id) {
        userCommandService.handle(new DeactivateUserCommand(id));
        return ResponseEntity.ok(ApiResponse.success(null, "User deactivated"));
    }

    @PutMapping("/{id}/username")
    public ResponseEntity<ApiResponse<Void>> changeName(@PathVariable UUID id, @RequestParam String username) {
        userCommandService.handle(new ChangeUserNameCommand(id, username));
        return ResponseEntity.ok(ApiResponse.success(null, "User name changed"));
    }

    @GetMapping("/{id}/tournaments")
    public ResponseEntity<ApiResponse<List<TournamentResponse>>> getTournamentsByUserId(@PathVariable UUID id){
        List<TournamentResponse> tournaments = userQueryService.handle(new GetTournamentsByUserIdQuery(id))
                .stream()
                .map(TournamentResponse::from)
                .toList();
        return ResponseEntity.ok(ApiResponse.success(tournaments, "Tournaments retrieved succesfully"));
    }
}
