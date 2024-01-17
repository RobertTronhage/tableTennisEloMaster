package se.tronhage.demo.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.tronhage.demo.entity.Player;
import se.tronhage.demo.service.MatchService;
import se.tronhage.demo.service.PlayerService;
import se.tronhage.demo.repository.PlayerRepo;

@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    PlayerService playerService;
    @Autowired
    MatchService matchService;
    @Autowired
    PlayerRepo playerRepo;

    @Override
    public void run(String... args) throws Exception {

    }
}
