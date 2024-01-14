package se.tronhage.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.tronhage.demo.entity.Match;
import se.tronhage.demo.entity.Player;
import se.tronhage.demo.repository.MatchRepo;

import java.util.List;

@Service
public class MatchService {
    @Autowired
    MatchRepo matchRepo;

    public void createMatch(Player player1, Player player2, Player winner) {
        Match match = new Match(player1, player2, winner);
        matchRepo.save(match);
    }
    public List<Match> getAllMatches(){
        return matchRepo.findAll();
    }

}