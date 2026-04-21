package pl.jdldev.bankapp.user.mapper;

import org.springframework.stereotype.Component;
import pl.jdldev.bankapp.user.api.UserResponse;
import pl.jdldev.bankapp.user.domain.User;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return new  UserResponse(
                user.getId(),
                user.getEmail(),
                user.getRole(),
                user.getStatus(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
