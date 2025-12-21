package com.nba.stats_api.controller;

import com.nba.stats_api.entity.Player;
import com.nba.stats_api.repository.PlayerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // GET /api/players - Get all players
    @GetMapping
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    // GET /api/players/1 - Get player by ID
    @GetMapping("/{id}")
    public Optional<Player> getPlayerById(@PathVariable Long id) {
        return playerRepository.findById(id);
    }

    // GET /api/players/search?name=LeBron James - Search by name
    @GetMapping("/search")
    public Optional<Player> searchByName(@RequestParam String name) {
        return playerRepository.findByName(name);
    }


}
