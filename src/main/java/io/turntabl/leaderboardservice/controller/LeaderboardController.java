package io.turntabl.leaderboardservice.controller;

import io.turntabl.leaderboardservice.controller.response.ProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/leaderboard")
public class LeaderboardController {

    private final LeaderboardFacade leaderboardFacade;

    @GetMapping("/all")
    public List<ProfileDto> getLeaderboard() {
        return leaderboardFacade.getLeaderboard();
    }

    @GetMapping("/honor")
    public List<ProfileDto> getLeaderboardOrderedByHonor(){
        return leaderboardFacade.getProfilesOrderedByHonor();
    }

    @GetMapping("/overall-rank")
    public List<ProfileDto> getLeaderboardOrderedByOverallRank(){
        return leaderboardFacade.getProfilesOrderedByOverallRank();
    }

    @GetMapping("/language/{language}")
    public List<ProfileDto> getLeaderboardByLanguage(@PathVariable("language") String language){
        return leaderboardFacade.getProfilesByLanguage(language);
    }

    @PostMapping("/add/{username}")
    public String addUser(@PathVariable("username") String username){
        leaderboardFacade.insertIntoDatabase(username);
        return "Inserted user";
    }
}
