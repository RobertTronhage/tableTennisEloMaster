package se.tronhage.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.tronhage.demo.entity.Player;
import se.tronhage.demo.repository.PlayerRepo;

import java.util.List;

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

        playerRepo.save(player1);
        playerRepo.save(player2);

    }

    public List<Player> getAllPlayers(){
        return playerRepo.findAll();
    }

}
