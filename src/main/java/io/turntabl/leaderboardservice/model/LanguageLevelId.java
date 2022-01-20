package io.turntabl.leaderboardservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class LanguageLevelId implements Serializable {

    private String name;

    private String profile;
}
