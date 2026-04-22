package pl.jdldev.bankapp.user.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jdldev.bankapp.common.exception.EmailAlreadyExistsException;
import pl.jdldev.bankapp.common.exception.UserNotFoundException;
import pl.jdldev.bankapp.user.api.RegisterUserRequest;
import pl.jdldev.bankapp.user.api.UserResponse;
import pl.jdldev.bankapp.user.domain.User;
import pl.jdldev.bankapp.user.domain.UserRole;
import pl.jdldev.bankapp.user.domain.UserStatus;
import pl.jdldev.bankapp.user.infrastructure.UserRepository;
import pl.jdldev.bankapp.user.mapper.UserMapper;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    public UserResponse register(RegisterUserRequest registerUserRequest) {
        if (userRepository.existsByEmail(registerUserRequest.email())) {
            throw new EmailAlreadyExistsException(registerUserRequest.email());
        }

        User user = new User(
                registerUserRequest.email(),
                passwordEncoder.encode(registerUserRequest.password()),
                UserStatus.ACTIVE,
                UserRole.CUSTOMER,
                LocalDateTime.now(),
                LocalDateTime.now());

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }

    @Transactional(readOnly = true)
    public UserResponse getUseryId(long userId) {
        User user = userRepository
                .getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return userMapper.toResponse(user);
    }
}