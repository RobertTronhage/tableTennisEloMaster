package se.tronhage.demo.Controller;

import org.springframework.web.bind.annotation.*;
import se.tronhage.demo.Entity.Player;

import java.util.ArrayList;

@RestController
@RequestMapping("/matches") public class MatchController {

    ArrayList<Player> allPlayers = new ArrayList<>();
    @GetMapping("/generatedata")
    public String createPlayer(){

        return "test";
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