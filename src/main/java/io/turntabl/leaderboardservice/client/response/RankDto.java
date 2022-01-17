package io.turntabl.leaderboardservice.client.response;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized @Builder
public class RankDto {

    Integer rank;
}
