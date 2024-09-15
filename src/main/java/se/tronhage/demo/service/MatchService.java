package se.tronhage.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.tronhage.demo.entity.Match;
import se.tronhage.demo.entity.Player;
import se.tronhage.demo.repository.MatchRepo;
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

    public void createMatch(Long player1Id, Long player2Id, Long winnerId) {

        Player player1 = playerService.getPlayerById(player1Id);
        Player player2 = playerService.getPlayerById(player2Id);
        Player winner;

        if (player1.getId().equals(winnerId)) {
            winner = player1;
            System.out.println("Winner is Player 1: " + winner.getName());
        } else if (player2.getId().equals(winnerId)) {
            winner = player2;
            System.out.println("Winner is Player 2: " + winner.getName());
        } else {
            System.out.println("Error: Winner ID does not match Player 1 or Player 2.");
            throw new IllegalArgumentException("Winner ID does not match any player.");
        }

        System.out.println(winner.getName());

        Match match = new Match(player1, player2, winner);
        double[] newPlayerElo = utilities.calculateElo(player1, player2, winner);

        player1.setElo(newPlayerElo[0]);
        player2.setElo(newPlayerElo[1]);

        player1.setMatchesPlayed(player1.getMatchesPlayed() + 1);
        player2.setMatchesPlayed(player2.getMatchesPlayed() + 1);

        if (winner.equals(player1)) {
            player1.setWins(player1.getWins() + 1);
            player2.setLosses(player2.getLosses() + 1);
        } else {
            player2.setWins(player2.getWins() + 1);
            player1.setLosses(player1.getLosses() + 1);
        }
        playerService.updatePlayerAfterMatch(player1, player2);
        matchRepo.save(match);
    }

    public List<Match> getAllMatches() {
        return matchRepo.findAll();
    }
}