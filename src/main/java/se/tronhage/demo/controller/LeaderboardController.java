package se.tronhage.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.tronhage.demo.entity.Leaderboard;
import se.tronhage.demo.entity.Player;
import se.tronhage.demo.repository.LeaderboardRepo;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/leaderboard") public class LeaderboardController {

    @Autowired
    LeaderboardRepo leaderboardRepo;
    @GetMapping("/get")
    public List<Leaderboard> getLeaderboard(){
        return leaderboardRepo.findAll();
    }
}