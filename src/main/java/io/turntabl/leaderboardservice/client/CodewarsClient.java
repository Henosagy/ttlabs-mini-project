package io.turntabl.leaderboardservice.client;

import io.turntabl.leaderboardservice.client.response.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;

@Component
public class CodewarsClient {
    
    private static final String BASE_USERS_PATH = "users/%s";

    private final RestTemplate restTemplate;

    private final String codewarsUri;

    public CodewarsClient(RestTemplateBuilder restTemplateBuilder, @Value("${codewars-uri}") String codewarsUri) {
        this.restTemplate = restTemplateBuilder.build();
        this.codewarsUri = codewarsUri;
    }

    public UserDto getUser(String username) {
        return restTemplate.getForObject(buildUrl(format(BASE_USERS_PATH, username)), UserDto.class);
    }

    private String buildUrl(String path) {
        return codewarsUri + path;
    }
}
