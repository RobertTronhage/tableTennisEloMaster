package se.tronhage.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.tronhage.demo.entity.Match;
import se.tronhage.demo.entity.MatchDTO;
import se.tronhage.demo.entity.Player;
import se.tronhage.demo.service.MatchService;
import se.tronhage.demo.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/matches") public class MatchController {

    @Autowired
    private MatchService matchService;

    ArrayList<Player> allMatches = new ArrayList<>();

    @GetMapping("/getallmatches")
    public List<Match> getAllMatches(){
        return matchService.getAllMatches();
    }

    @PostMapping("/creatematch")
    public String createMatch(@RequestBody MatchDTO matchDTO){
        try {
            matchService.createMatch(matchDTO.getPlayer1Id(),matchDTO.getPlayer2Id(),matchDTO.getWinnerId());
            return String.valueOf(new ResponseEntity<>("Match added", HttpStatus.CREATED));

        } catch (Exception e) {
            return String.valueOf(new ResponseEntity<>("Error adding match: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}