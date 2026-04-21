package pl.jdldev.bankapp.user.api;

import pl.jdldev.bankapp.user.domain.UserRole;
import pl.jdldev.bankapp.user.domain.UserStatus;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String email,
        UserRole role,
        UserStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}