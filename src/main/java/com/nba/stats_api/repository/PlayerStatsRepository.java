package com.nba.stats_api.repository;

import com.nba.stats_api.entity.PlayerStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerStatsRepository extends JpaRepository<PlayerStats, Long> {

    // Spring Boot automatically provides CRUD methods

    // Custom query: find all stats for a specific player
    List<PlayerStats> findByPlayerId(Long playerId);
}