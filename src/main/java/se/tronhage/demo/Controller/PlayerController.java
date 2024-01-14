package se.tronhage.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.tronhage.demo.Entity.Player;
import se.tronhage.demo.Service.PlayerService;

import java.util.ArrayList;

@RestController
@RequestMapping("/players") public class PlayerController {

    @Autowired
    private PlayerService playerService;

    ArrayList<Player> allPlayers = new ArrayList<>();
    @GetMapping("/generatedata")
    public String createPlayer(){
        return "he funk";
    }
    @GetMapping("/getplayerdata")
    public ArrayList<Player>getPlayerData(){
        return allPlayers;
    }
    @PostMapping("/setplayerdata")
    public String addPlayer(@RequestBody Player player){
        //Ska man ha nå if-sats här?
        return "new player added";
    }
}
