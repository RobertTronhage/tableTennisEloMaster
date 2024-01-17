package se.tronhage.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.tronhage.demo.entity.Player;
import se.tronhage.demo.repository.PlayerRepo;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    PlayerRepo playerRepo;

    public void addNewPlayer(String name){
        Player player = new Player(name);
        playerRepo.save(player);
        System.out.println("added player");
    }

    public void updatePlayerElo(Player player1,Player player2){
        playerRepo.saveAll(List.of(player1, player2));
    }

    public Player getPlayerById(long id) {
        Optional<Player> optionalPlayer = playerRepo.findById(id);
        Player player = null;
        if (optionalPlayer.isPresent()) {
            player = optionalPlayer.get();
        }
        return player;
    }

    public List<Player> getAllPlayers(){
        return playerRepo.findAll();
    }

}
