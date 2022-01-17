package io.turntabl.leaderboardservice.converter;

import io.turntabl.leaderboardservice.controller.response.LanguageLevelDto;
import io.turntabl.leaderboardservice.controller.response.ProfileDto;
import io.turntabl.leaderboardservice.model.LanguageLevel;
import io.turntabl.leaderboardservice.model.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ProfileToProfileDtoConverter implements Converter<Profile, ProfileDto> {

    @Override
    public ProfileDto convert(Profile source) {
        return ProfileDto.builder()
                .username(source.getId())
                .name(source.getName())
                .clan(source.getClan())
                .honour(source.getHonour())
                .overallRank(source.getOverallRank())
                .languages(convertLanguages(source.getLanguageLevels()))
                .build();
    }

    public List<LanguageLevelDto> convertLanguages(List<LanguageLevel> languageLevels) {
        return languageLevels.stream()
                .map(languageLevel -> LanguageLevelDto.builder()
                        .name(languageLevel.getName())
                        .rank(languageLevel.getRank())
                        .build())
                .collect(toList());
    }
}
