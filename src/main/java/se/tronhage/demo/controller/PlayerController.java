package se.tronhage.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.tronhage.demo.entity.Player;
import se.tronhage.demo.service.PlayerService;
import java.util.List;

@RestController
@RequestMapping("/players") public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/getallplayers")
    public List<Player> getPlayerData(){
        return playerService.getAllPlayers();
    }
    @PostMapping("/addplayer")
    public String addPlayer(@RequestBody Player player){
        try {
            playerService.addNewPlayer(player.getName());
            return String.valueOf(new ResponseEntity<>("New player added", HttpStatus.CREATED));
        } catch (Exception e) {
            return String.valueOf(new ResponseEntity<>("Error adding player: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
