package io.turntabl.leaderboardservice.client.response;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

@Value
@Jacksonized
@Builder
public class RanksDto {

    RankDto overall;

    Map<String, RankDto> languages;
}
