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
public class Quest {

    @Id
    private int questId;
    private String questName;
    private int plantId;
    private int antidoteId;
    private int questGiverId; // FK to the GameCharacter table
    private int patientId; // FK to the GameCharacter table
    private String startText; // Introductory cutscene
    private String endText; // Concluding cutscene
    private int requiredLevel; // Determines when quest unlocks
    private int xpValue;
}
