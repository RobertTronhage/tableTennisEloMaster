package se.tronhage.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.tronhage.demo.Entity.Match;
import se.tronhage.demo.Entity.Player;
import se.tronhage.demo.repository.MatchRepo;

@Service
public class MatchService {
    @Autowired
    MatchRepo matchRepo;

    public void createMatch(Player player1, Player player2, Player winner) {
        Match match = new Match(player1, player2, winner);
        matchRepo.save(match);
    }
}
