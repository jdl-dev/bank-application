package pl.jdldev.bankapp.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jdldev.bankapp.user.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<User> getUserById(Long id);
}