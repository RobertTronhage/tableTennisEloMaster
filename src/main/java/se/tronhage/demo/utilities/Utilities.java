package se.tronhage.demo.utilities;

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
        double oddsOfWinningForPlayer1 = 1.0 / (1.0 + Math.pow(10, (player2.getElo() - player1.getElo()) / 400.0));
        double oddsOfWinningForPlayer2 = 1.0 / (1.0 + Math.pow(10, (player1.getElo() - player2.getElo()) / 400.0));

        double calcElo1 = player1.getElo() + constant * ((player1.equals(winner) ? 1 : 0) - oddsOfWinningForPlayer1);
        double calcElo2 = player2.getElo() + constant * ((player2.equals(winner) ? 1 : 0) - oddsOfWinningForPlayer2);

        player1.setElo(calcElo1);
        player2.setElo(calcElo2);

        playerRepo.saveAll(List.of(player1,player2));
    }

}
