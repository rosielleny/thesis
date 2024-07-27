package org.druid.entity.original;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* This entity reflects the Quest database table*/
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
    private int xpValue; // The number of experience points the player is rewarded with
    private String stage1Text; // E.g. "Find the plant."
    private String stage2Text; // E.g. "Make the antidote."
    private String stage3Text; // E.g. "Hand in the quest."
}
