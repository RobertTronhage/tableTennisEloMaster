package se.tronhage.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.tronhage.demo.entity.Player;

@Repository
public interface PlayerRepo extends JpaRepository <Player, Long> {

}
