package io.turntabl.leaderboardservice.controller.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LanguageLevelDto {

    String name;

    Integer rank;
}
