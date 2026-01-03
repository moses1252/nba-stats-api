package com.nba.stats_api.controller;

import com.nba.stats_api.entity.PlayerStats;
import com.nba.stats_api.repository.PlayerStatsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stats")
public class PlayerStatsController {
    private final PlayerStatsRepository playerStatsRepository;

    public PlayerStatsController(PlayerStatsRepository playerStatsRepository) {
        this.playerStatsRepository = playerStatsRepository;
    }

    //GET /api/stats/player/1 - get stats for player id
    @GetMapping("/player/{playerId}")
    public List<PlayerStats> getStatsBylayerId(@PathVariable Long playerId) {
        return playerStatsRepository.findByPlayerId(playerId);
    }
}
