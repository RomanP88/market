package ru.gb.market.auth.repositiries;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.auth.entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
