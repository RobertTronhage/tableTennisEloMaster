package se.tronhage.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.tronhage.demo.entity.Match;
import se.tronhage.demo.entity.MatchDTO;
import se.tronhage.demo.entity.Player;
import se.tronhage.demo.repository.MatchRepo;
import se.tronhage.demo.repository.PlayerRepo;
import se.tronhage.demo.utilities.Utilities;

import java.util.List;

@Service
public class MatchService {
    @Autowired
    MatchRepo matchRepo;
    @Autowired
    private Utilities utilities;
    @Autowired
    PlayerService playerService;

    public void createMatch(Long player1Id,Long player2Id,Long winnerId) {

        Player player1 = playerService.getPlayerById(player1Id);
        Player player2 = playerService.getPlayerById(player2Id);
        Player winner = null;

        if (player1.getId().equals(winnerId)){
            winner = player1;
        }else{
            winner = player2;
        }

        Match match = new Match(player1, player2, winner);
        double [] newPlayerElo = utilities.calculateElo(player1,player2,winner);

        player1.setElo(newPlayerElo[0]);
        player2.setElo(newPlayerElo[1]);
        playerService.updatePlayerElo(player1,player2);

        matchRepo.save(match);
    }
    public List<Match> getAllMatches(){
        return matchRepo.findAll();
    }
}