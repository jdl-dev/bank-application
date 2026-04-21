package pl.jdldev.bankapp.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jdldev.bankapp.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}