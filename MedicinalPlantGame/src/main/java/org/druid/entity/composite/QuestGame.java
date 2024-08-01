package org.druid.entity.composite;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.druid.entity.original.Quest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestGame {

    @Id
    private int questId;
    private String questName;
    private int plantId;
    private int antidoteId;
    private String startText; // Introductory cutscene
    private String endText; // Concluding cutscene
    private int requiredLevel; // Determines when quest unlocks
    private int xpValue; // The number of experience points the player is rewarded with
    private List<String> stagesText;// E.g. "Find the plant.", "Make the antidote.", "Hand in the quest."


    // New values

    private String questGiverName;
    private String questGiverPicture;
    private String patientName;
    private String patientPicture;
    private String playerQuestStatus;
    private String playerStage;

    public void setValuesUsingQuest(Quest quest) {

        this.questId = quest.getQuestId();
        this.questName = quest.getQuestName();
        this.plantId = quest.getPlantId();
        this.antidoteId = quest.getAntidoteId();
        this.startText = quest.getStartText();
        this.endText = quest.getEndText();
        this.requiredLevel = quest.getRequiredLevel();
        this.xpValue = quest.getXpValue();
        this.stagesText = Arrays.asList(quest.getStage1Text(), quest.getStage2Text(), quest.getStage3Text());
    }
}
