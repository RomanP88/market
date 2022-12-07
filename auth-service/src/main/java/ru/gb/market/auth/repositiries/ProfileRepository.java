package ru.gb.market.auth.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.auth.entities.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
