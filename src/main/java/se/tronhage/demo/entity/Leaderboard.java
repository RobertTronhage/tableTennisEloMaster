package se.tronhage.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="leaderboard_view")
public class Leaderboard {
    @Id
    Long id;

}
