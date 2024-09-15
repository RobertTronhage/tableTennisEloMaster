package se.tronhage.demo.utilities;

/**
Author Robert Tronhage
version 1.0
Purpose of this file is to take in ELO from PlayerRepo and update the ELO based on result of match.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.tronhage.demo.entity.Player;
import se.tronhage.demo.repository.PlayerRepo;
import se.tronhage.demo.service.PlayerService;

import java.util.List;


@Component
public class Utilities {

    @Autowired
    PlayerRepo playerRepo;

    @Autowired
    PlayerService playerService;

    //if one player has played fewer than 5 matches, set constant to lower?

    public int getConstant(Player player){
        int constant;
        int noOfGames = 0; // = player.getgameshistory.size;

        if (noOfGames > 5){
            constant = 30;
        }else {
            constant = 15;
        }
        return constant;
    }

    public double[] calculateElo(Player player1, Player player2, Player winner) {
        int constantP1 = getConstant(player1);
        int constantP2 = getConstant(player2);

        double oddsOfWinningForPlayer1 = 1.0 / (1.0 + Math.pow(10, (player2.getElo() - player1.getElo()) / 400.0));
        double oddsOfWinningForPlayer2 = 1.0 / (1.0 + Math.pow(10, (player1.getElo() - player2.getElo()) / 400.0));

        double calcElo1 = player1.getElo() + constantP1 * ((player1.getId().equals(winner.getId()) ? 1 : 0) - oddsOfWinningForPlayer1);
        double calcElo2 = player2.getElo() + constantP2 * ((player2.getId().equals(winner.getId()) ? 1 : 0) - oddsOfWinningForPlayer2);

        System.out.println(calcElo1 + "player1");
        System.out.println(calcElo2 + "player2");
        System.out.println(winner.getName());

        double newElo1 = Math.round(calcElo1);
        double newElo2 = Math.round(calcElo2);

        return new double[]{newElo1, newElo2};
    }
}
