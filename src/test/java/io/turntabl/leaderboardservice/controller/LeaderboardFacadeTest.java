package io.turntabl.leaderboardservice.controller;

import static org.junit.jupiter.api.Assertions.*;

import io.turntabl.leaderboardservice.controller.response.LanguageLevelDto;
import io.turntabl.leaderboardservice.controller.response.ProfileDto;
import io.turntabl.leaderboardservice.converter.ProfileToProfileDtoConverter;
import io.turntabl.leaderboardservice.model.Profile;
import io.turntabl.leaderboardservice.service.LeaderboardRepositoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LeaderboardFacadeTest {
    @Mock
    private LeaderboardRepositoryService leaderboardRepositoryService;

    @Mock
    private ProfileToProfileDtoConverter profileToProfileDtoConverter;

    @Mock
    private LeaderboardFacade underTest;

//    @BeforeEach
//    void setUp(){
//        underTest = new LeaderboardFacade(leaderboardRepositoryService, profileToProfileDtoConverter);
//    }

    @Test
    void getLeaderboard() {
        ProfileDto profileDto = ProfileDto.builder()
                .username("lameiraatt")
                .name("Ana Lameira")
                .build();
        List<ProfileDto> expectedResult = List.of(profileDto);

        when(underTest.getLeaderboard())
                .thenReturn(List.of(profileDto));


        assertThat(underTest.getLeaderboard()).isEqualTo(expectedResult);

    }

    @Test
    void getLeaderboardByHonor(){
        ProfileDto profile1 = ProfileDto.builder()
                .username("lameiraatt")
                .name("Ana Lameira")
                .honour(152)
                .build();

        ProfileDto profile2 = ProfileDto.builder()
                .username("henosagy")
                .name("Henry")
                .honour(50)
                .build();

        List<ProfileDto> expectedResult = List.of(profile1, profile2);

        when(underTest.getProfilesOrderedByHonor()).thenReturn(List.of(profile1, profile2));

        assertThat(underTest.getProfilesOrderedByHonor()).isEqualTo(expectedResult);
    }

    @Test
    void getLeaderboardByOverallRank(){
        ProfileDto profile1 = ProfileDto.builder()
                .username("lameiraatt")
                .name("Ana Lameira")
                .overallRank(-5)
                .build();

        ProfileDto profile2 = ProfileDto.builder()
                .username("henosagy")
                .name("Henry")
                .overallRank(-6)
                .build();

        List<ProfileDto> expectedResult = List.of(profile1, profile2);

        when(underTest.getProfilesOrderedByHonor()).thenReturn(List.of(profile1, profile2));

        assertThat(underTest.getProfilesOrderedByHonor()).isEqualTo(expectedResult);
    }
}