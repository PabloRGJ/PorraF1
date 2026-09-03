package com.api.porraf1.user.infrastructure.persistence.mapper;

import com.api.porraf1.user.domain.model.User;
import com.api.porraf1.user.infrastructure.persistence.entity.UserJpaEntity;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public static UserJpaEntity toJpaEntity(User user) {
        return UserJpaEntity.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPasswordHash())
                .active(user.isActive())
                .build();
    }

    public static User toDomain(UserJpaEntity entity) {
        return User.reconstitute(
                entity.getId(),
                entity.getUserName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.isActive()
        );
    }


}
