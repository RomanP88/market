package ru.gb.market.auth.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.auth.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
