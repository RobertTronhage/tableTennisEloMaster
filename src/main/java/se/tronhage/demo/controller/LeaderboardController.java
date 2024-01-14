package se.tronhage.demo.controller;

import org.springframework.web.bind.annotation.*;
import se.tronhage.demo.entity.Player;

import java.util.ArrayList;

@RestController
@RequestMapping("/leaderboard") public class LeaderboardController {

    ArrayList<Player> allPlayers = new ArrayList<>();
    @GetMapping("/generatedata")
    public String createPlayer(){
        //how does one generate data?
        return "data generated";
    }
    @GetMapping("/getplayerdata")
    public ArrayList<Player>getPlayerData(){
        return allPlayers;
    }

    @PostMapping("/setplayerdata")
    public String addUser(@RequestBody Player player){

        //Ska man ha nå if-sats här?
        return "new player added";
    }
}