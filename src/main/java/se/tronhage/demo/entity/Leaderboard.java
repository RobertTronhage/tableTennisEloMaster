package se.tronhage.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="leaderboard_view")
public class Leaderboard {
    @Id
    @Column(name = "player_id")
    Long playerId;
    @Column(name="name")
    String playerName;
    @Column(name = "elo")
    double elo;

    @Column(name = "matches_played")
    int matchesPlayed;

    @Column(name = "wins")
    int wins;

    @Column(name = "losses")
    int losses;

    @Transient
    private double winLossRatio;

    public Leaderboard() {
    }

    public Leaderboard(Long playerId, String playerName, double elo, int matchesPlayed, int wins, int losses) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.elo = elo;
        this.matchesPlayed = matchesPlayed;
        this.wins = wins;
        this.losses = losses;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public double getElo() {
        return elo;
    }

    public void setElo(double elo) {
        this.elo = elo;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public double getWinLossRatio() {
        return losses == 0 ? wins : (double) wins / losses;
    }

}
