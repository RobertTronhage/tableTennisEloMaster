package se.tronhage.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.tronhage.demo.Entity.Match;

@Repository
public interface MatchRepo extends JpaRepository<Match,Long> {
}
