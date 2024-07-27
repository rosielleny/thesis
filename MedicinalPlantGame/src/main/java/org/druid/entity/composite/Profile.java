package org.druid.entity.composite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    private int playerId;
    private String playerName;
    private String playerEmail;
    private String playerPhone;
    private String playerPicture;
    private int playerTotalXP;
    private int playerLevel;

    // New calculated data
    private int questsCompleted;
    private int pagesUnlocked;
    private int antidotesMade;
}
