package ru.gb.market.auth.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.auth.entities.Profile;
import ru.gb.market.auth.repositiries.ProfileRepository;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    public Profile save (Profile profile){
        return profileRepository.save(profile);
    }
}
