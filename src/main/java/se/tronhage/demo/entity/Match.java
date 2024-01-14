package se.tronhage.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "game_matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player1")  // Update to match the actual column name in the database
    private Player player1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player2")  // Update to match the actual column name in the database
    private Player player2;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "winning_player")  // Update to match the actual column name in the database
    private Player winningPlayer;

    public Match() {
    }

    public Match(Player player1, Player player2, Player winningPlayer) {
        this.player1 = player1;
        this.player2 = player2;
        this.winningPlayer = winningPlayer;
    }

    public Long getId() {
        return id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getWinningPlayer() {
        return winningPlayer;
    }
}