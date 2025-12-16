package com.nba.stats_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="players")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @Column(name="player_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="position")
    private String position;
    @Column(name="age")
    private Integer age;
    @Column(name="team")
    private String team;

}
