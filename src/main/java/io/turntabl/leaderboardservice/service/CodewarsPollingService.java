package io.turntabl.leaderboardservice.service;

import io.turntabl.leaderboardservice.client.CodewarsClient;
import io.turntabl.leaderboardservice.converter.UserDtoToProfileConverter;
import io.turntabl.leaderboardservice.model.Profile;
import io.turntabl.leaderboardservice.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CodewarsPollingService {

    private final CodewarsClient codewarsClient;
    private final UserDtoToProfileConverter userDtoToProfileConverter;
    private final ProfileRepository profileRepository;

    @Scheduled(fixedDelay = 10000)
    public void updateProfiles() {
        profileRepository.findAll().stream()
                .map(Profile::getId)
                .map(codewarsClient::getUser)
                .filter(Objects::nonNull)
                .map(userDtoToProfileConverter::convert)
                .forEach(profileRepository::save);
    }
}
