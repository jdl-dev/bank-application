package pl.jdldev.bankapp.user.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jdldev.bankapp.user.api.RegisterUserRequest;
import pl.jdldev.bankapp.user.domain.User;
import pl.jdldev.bankapp.user.domain.UserRole;
import pl.jdldev.bankapp.user.domain.UserStatus;
import pl.jdldev.bankapp.user.infrastructure.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(RegisterUserRequest registerUserRequest) {
        if (userRepository.existsByEmail(registerUserRequest.email())) {
            throw new IllegalArgumentException("Given email already exists.");
        }

        User user = new User(
                registerUserRequest.email(),
                passwordEncoder.encode(registerUserRequest.password()),
                UserStatus.ACTIVE,
                UserRole.CUSTOMER,
                LocalDateTime.now(),
                LocalDateTime.now());

        userRepository.save(user);
    }
}
