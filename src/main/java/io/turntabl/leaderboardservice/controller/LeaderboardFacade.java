package io.turntabl.leaderboardservice.controller;

import io.turntabl.leaderboardservice.controller.response.LanguageLevelDto;
import io.turntabl.leaderboardservice.controller.response.ProfileDto;
import io.turntabl.leaderboardservice.converter.ProfileToProfileDtoConverter;
import io.turntabl.leaderboardservice.service.LeaderboardRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Component
public class LeaderboardFacade {

    private final LeaderboardRepositoryService leaderboardRepositoryService;
    private final ProfileToProfileDtoConverter profileToProfileDtoConverter;

    public List<ProfileDto> getLeaderboard() {
        return leaderboardRepositoryService.getProfiles().stream()
                .map(profileToProfileDtoConverter::convert)
                .collect(toList());
    }

    public List<ProfileDto> getProfilesOrderedByHonor(){
        List<ProfileDto> profileList = new ArrayList<>();
        profileList.addAll(getLeaderboard());
        Collections.sort(profileList, Comparator.comparingInt(ProfileDto::getHonour));
        Collections.reverse(profileList);
        return profileList;
    }

    public List<ProfileDto> getProfilesOrderedByOverallRank(){
        List<ProfileDto> profileList = new ArrayList<>();
        profileList.addAll(getLeaderboard());
        Collections.sort(profileList, Comparator.comparingInt(ProfileDto::getOverallRank));
        Collections.reverse(profileList);
        return profileList;
    }

    public List<ProfileDto> getProfilesByLanguage(String language){
        List<ProfileDto> profileList = new ArrayList<>();

        getLeaderboard().stream().forEach(profile ->{
            if (profile.getLanguages() != null){
                for (LanguageLevelDto d :
                        profile.getLanguages()) {
                    if (d.getName().equals(language)){
                        profileList.add(profile);
                    }
                }
            }

        });

        return profileList;
    }
}
