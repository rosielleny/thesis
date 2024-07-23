package org.game.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameLevel {

    @Id
    private int gameLevelId; // Doubles as the level number
    private int requiredXP; // Required xp to reach this level
}
