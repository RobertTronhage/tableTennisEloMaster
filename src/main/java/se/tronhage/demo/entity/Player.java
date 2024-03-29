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
