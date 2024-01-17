package se.tronhage.demo.utilities;

/*
Author Robert Tronhage
version 1.0
Purpose of this file is to take in ELO from PlayerRepo and update the ELO based on result of match.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.tronhage.demo.entity.Player;
import se.tronhage.demo.repository.PlayerRepo;

import java.util.List;



@Component
public class Utilities {

    @Autowired
    PlayerRepo playerRepo;

    public void calculateElo(Player player1, Player player2, Player winner) {
        int constant = 30;

        // Calculate odds of winning for each player
        double oddsOfWinningForPlayer1 = 1.0 / (1.0 + Math.pow(10, (player2.getElo() - player1.getElo()) / 400.0));
        double oddsOfWinningForPlayer2 = 1.0 / (1.0 + Math.pow(10, (player1.getElo() - player2.getElo()) / 400.0));

        System.out.println(oddsOfWinningForPlayer1);
        System.out.println(oddsOfWinningForPlayer2);
        // Calculate new Elo ratings
        double calcElo1 = player1.getElo() + constant * ((player1.getId().equals(winner.getId()) ? 1 : 0) - oddsOfWinningForPlayer1);
        double calcElo2 = player2.getElo() + constant * ((player2.getId().equals(winner.getId()) ? 1 : 0) - oddsOfWinningForPlayer2);

        // Round the calculated Elo ratings to the nearest integer
        double newElo1 = Math.round(calcElo1);
        double newElo2 = Math.round(calcElo2);

        // Update player's Elo ratings
        player1.setElo(newElo1);
        player2.setElo(newElo2);

        // Save updated players to the repository
        playerRepo.saveAll(List.of(player1, player2));
    }

}
