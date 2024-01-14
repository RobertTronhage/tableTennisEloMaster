package se.tronhage.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="leaderboard_view")
public class Leaderboard {
    @Id
    Long PlayerId;
    String playerName;
    double elo;

    public Leaderboard(Long playerId, String playerName, double elo) {
        PlayerId = playerId;
        this.playerName = playerName;
        this.elo = elo;
    }

    public Leaderboard() {
    }

    public Long getPlayerId() {
        return PlayerId;
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
}
