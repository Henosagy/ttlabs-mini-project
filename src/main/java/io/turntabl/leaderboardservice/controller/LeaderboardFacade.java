package io.turntabl.leaderboardservice.controller;

import io.turntabl.leaderboardservice.controller.response.LanguageLevelDto;
import io.turntabl.leaderboardservice.controller.response.ProfileDto;
import io.turntabl.leaderboardservice.converter.ProfileToProfileDtoConverter;
import io.turntabl.leaderboardservice.model.Profile;
import io.turntabl.leaderboardservice.repository.ProfileRepository;
import io.turntabl.leaderboardservice.service.LeaderboardRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Component
@Slf4j
public class LeaderboardFacade {

    private final LeaderboardRepositoryService leaderboardRepositoryService;
    private final ProfileToProfileDtoConverter profileToProfileDtoConverter;
    private final ProfileRepository profileRepository;


    public List<ProfileDto> getLeaderboard() {
        return leaderboardRepositoryService.getProfiles().stream()
                .map(profileToProfileDtoConverter::convert)
                .collect(toList());
    }

    public List<ProfileDto> getProfilesOrderedByHonor(){
        return leaderboardRepositoryService.getProfiles().stream()
                .sorted(Comparator.comparingInt(Profile::getHonour).reversed())
                .map(profileToProfileDtoConverter::convert)
                .collect(toList());
    }

    public List<ProfileDto> getProfilesOrderedByOverallRank(){
        return leaderboardRepositoryService.getProfiles().stream()
                .sorted(Comparator.comparingInt(Profile::getOverallRank).reversed())
                .map(profileToProfileDtoConverter::convert)
                .collect(toList());
    }

    public List<ProfileDto> getProfilesByLanguage(String language){
        return leaderboardRepositoryService.getProfiles().stream()
                .filter(profile -> profile.getLanguageLevels().stream()
                        .anyMatch(languageLevel -> languageLevel.getName().equals(language)))
                .sorted(Comparator.comparingInt(Profile::getHonour).reversed())
                .map(profileToProfileDtoConverter::convert)
                .collect(toList());
    }

    public void insertIntoDatabase(String username){
        profileRepository.insertIntoDatabase(username);
    }
}
