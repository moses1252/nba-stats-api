package com.nba.stats_api.loader;

import com.nba.stats_api.entity.Player;
import com.nba.stats_api.entity.PlayerStats;
import com.nba.stats_api.repository.PlayerRepository;
import com.nba.stats_api.repository.PlayerStatsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class DataLoader implements CommandLineRunner {

    private final PlayerRepository playerRepository;
    private final PlayerStatsRepository playerStatsRepository;

    public DataLoader(PlayerRepository playerRepository,
                      PlayerStatsRepository playerStatsRepository) {
        this.playerRepository = playerRepository;
        this.playerStatsRepository = playerStatsRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Check if data already exists
        if (playerRepository.count() > 0) {
            System.out.println("Data already loaded. Skipping...");
            return;
        }

        System.out.println("Loading NBA player data from CSV...");

        // Read the CSV file
        ClassPathResource resource = new ClassPathResource("data/nba-player-stats.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));

        String line;
        boolean firstLine = true;

        while ((line = reader.readLine()) != null) {

            // Skip header row
            if (firstLine) {
                firstLine = false;
                continue;
            }

            // Split by semicolon (your CSV uses ;)
            String[] data = line.split(";");

            // Create Player entity
            Player player = new Player();
            player.setName(data[1]);        // Player name
            player.setPosition(data[2]);    // Position
            player.setAge(Integer.parseInt(data[3])); // Age
            player.setTeam(data[4]);        // Team

            // Save player to database
            Player savedPlayer = playerRepository.save(player);

            // Create PlayerStats entity
            PlayerStats stats = new PlayerStats();
            stats.setPlayer(savedPlayer);
            stats.setGamesPlayed(Integer.parseInt(data[5]));     // G
            stats.setGamesStarted(Integer.parseInt(data[6]));    // GS
            stats.setMinutesPerGame(Double.parseDouble(data[7])); // MP
            stats.setPointsPerGame(Double.parseDouble(data[28])); // PTS

            // TRB (Total Rebounds) is at index 23
            stats.setReboundsPerGame(Double.parseDouble(data[23])); // TRB
            stats.setAssistsPerGame(Double.parseDouble(data[24])); // AST
            stats.setStealsPerGame(Double.parseDouble(data[25]));  // STL
            stats.setBlocksPerGame(Double.parseDouble(data[26]));  // BLK

            stats.setFieldGoalPercentage(Double.parseDouble(data[10])); // FG%
            stats.setThreePointPercentage(Double.parseDouble(data[13])); // 3P%
            stats.setFreeThrowPercentage(Double.parseDouble(data[20])); // FT%

            // Save stats to database
            playerStatsRepository.save(stats);
        }

        reader.close();

        System.out.println("✅ Data loaded successfully!");
        System.out.println("Total players: " + playerRepository.count());
    }
}