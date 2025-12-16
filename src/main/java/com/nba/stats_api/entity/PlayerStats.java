package com.nba.stats_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "player_stats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stats_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Column(name = "games_played")
    private Integer gamesPlayed;

    @Column(name = "games_started")
    private Integer gamesStarted;

    @Column(name = "minutes_per_game")
    private Double minutesPerGame;

    @Column(name = "points_per_game")
    private Double pointsPerGame;

    @Column(name = "rebounds_per_game")
    private Double reboundsPerGame;

    @Column(name = "assists_per_game")
    private Double assistsPerGame;

    @Column(name = "steals_per_game")
    private Double stealsPerGame;

    @Column(name = "blocks_per_game")
    private Double blocksPerGame;

    @Column(name = "field_goal_percentage")
    private Double fieldGoalPercentage;

    @Column(name = "three_point_percentage")
    private Double threePointPercentage;

    @Column(name = "free_throw_percentage")
    private Double freeThrowPercentage;
}