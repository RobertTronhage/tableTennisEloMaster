package se.tronhage.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="player_id")
    Long id;

    String name;

    @Column(columnDefinition = "float default 800.0")
    double elo = 800;

    @Column(name = "matches_played", columnDefinition = "int default 0")
    int matchesPlayed = 0;
    @Column(name = "wins", columnDefinition = "int default 0")
    int wins = 0;
    @Column(name = "losses", columnDefinition = "int default 0")
    int losses = 0;

    public double getWinLossRatio(){
        if (losses==0){
            return wins == 0 ? 0 : wins;
        }
        return (double) wins / losses;
    }

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getElo() {
        return elo;
    }

    public void setElo(double elo) {
        this.elo = elo;
    }

    //lägga till win loss ratio??
    //win-streak??
    //achivements??
}
