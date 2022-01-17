package io.turntabl.leaderboardservice.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileTest {

    @Mock
    private Profile profile;

    @Test
    void setLanguageLevels() {
        LanguageLevel level1 = mock(LanguageLevel.class);
        LanguageLevel level2 = mock(LanguageLevel.class);

        List<LanguageLevel> languageLevels = List.of(level1, level2);

        when(profile.setLanguageLevels(languageLevels)).thenReturn(profile);

        assertThat(profile.setLanguageLevels(languageLevels)).isEqualTo(profile);
    }
}