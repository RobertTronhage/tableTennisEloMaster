package se.tronhage.demo.Entity;

import jakarta.persistence.*;

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player1_id")
    Player player1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player2_id")
    Player player2;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "winning_player_id")
    Player winningPlayer;

    public Match() {
    }

    public Match(Player player1, Player player2, Player winningPlayer) {
        this.player1 = player1;
        this.player2 = player2;
        this.winningPlayer = winningPlayer;
    }
}