package se.tronhage.demo.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.tronhage.demo.entity.Player;
import se.tronhage.demo.service.MatchService;
import se.tronhage.demo.service.PlayerService;
import se.tronhage.demo.repository.PlayerRepo;

import java.util.ArrayList;
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


//        while (true) {
//            System.out.println("""
//                    Add player
//                    match yo
//                    """);
//            int choice = input.nextInt();
//            input.nextLine();
//
//            switch(choice) {
//                case 1 -> {
//                    System.out.println("Enter player IDs and winning player ID: ");
//                    System.out.println("Player 1 ID: ");
//                    Long player1Id = input.nextLong();
//                    System.out.println("Player 2 ID: ");
//                    Long player2Id = input.nextLong();
//                    System.out.println("Winning Player ID: ");
//                    Long winningPlayerId = input.nextLong();
//
//                    // Fetch players from the repository
//                    Player player1 = playerRepo.findById(player1Id).orElse(null);
//                    Player player2 = playerRepo.findById(player2Id).orElse(null);
//                    Player winner = playerRepo.findById(winningPlayerId).orElse(null);
//
//                    // Check if players and winner are found
//                    if (player1 != null && player2 != null && winner != null) {
//                        matchService.createMatch(player1, player2, winner);
//                        System.out.println("Match created successfully!");
//                    } else {
//                        System.out.println("Error: One or more players not found!");
//                    }
//                }
//                case 0 -> {
//                    System.out.println("Exiting...");
//                    System.exit(0);
//                }
//            }
//        }
    }
}
