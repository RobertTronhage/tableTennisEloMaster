package se.tronhage.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.tronhage.demo.entity.Leaderboard;

@Repository
public interface LeaderboardRepo extends JpaRepository<Leaderboard,Long> {
}
