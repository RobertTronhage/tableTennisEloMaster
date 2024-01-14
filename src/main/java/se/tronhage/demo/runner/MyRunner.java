package se.tronhage.demo.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.tronhage.demo.Entity.Player;
import se.tronhage.demo.Service.MatchService;
import se.tronhage.demo.Service.PlayerService;
import se.tronhage.demo.repository.PlayerRepo;

import java.util.Scanner;

@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    PlayerService playerService;
    @Autowired
    MatchService matchService;
    @Autowired
    PlayerRepo playerRepo;
    Scanner input = new Scanner(System.in);

    @Override
    public void run(String... args) throws Exception {
        System.out.println("test");

        while (true) {
            System.out.println("""
                    Add player
                    match yo
                    """);
            int choice = input.nextInt();
            input.nextLine();

            switch(choice) {
                case 1 -> {
                    System.out.println("Enter name: ");
                    String name = input.nextLine();
                    playerService.addNewPlayer(name);
                }
                case 2 ->{
                    Long a = 3L;
                    Player player1 = playerRepo.findById(3L).get();
                    Player player2 = playerRepo.findById(3L).get();
                    Player winner = playerRepo.findById(3L).get();
                    matchService.createMatch(player1, player2, winner);

                }
            }
        }
    }
}
