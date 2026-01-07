package com.nba.stats_api.repository;

import com.nba.stats_api.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    // Spring Boot automatically provides:
    // - save(Player player)
    // - findAll()
    // - findById(Long id)
    // - deleteById(Long id)
    // - count()

    //custom query methods (spring boot generates SQL automatically)
    Optional<Player> findByName(String name);

    // Find all players on a specific team
    List<Player> findByTeam(String team);
}
