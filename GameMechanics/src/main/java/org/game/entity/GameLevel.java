package org.game.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* This entity reflects a database table which stores the levels it is possible for a player to be
* and the amount of experience points they need to reach that level*/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameLevel {

    @Id
    private byte gameLevelId; // Doubles as the level number
    private int requiredXP; // Required xp to reach this level
}
