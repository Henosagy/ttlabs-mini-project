package io.turntabl.leaderboardservice.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ProfileDto {

    String username;

    String name;

    String clan;

    Integer honour;

    @JsonProperty(value = "overall_rank")
    Integer overallRank;

    List<LanguageLevelDto> languages;
}
