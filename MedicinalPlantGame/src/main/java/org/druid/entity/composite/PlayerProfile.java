package org.druid.entity.composite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.druid.entity.original.Player;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerProfile {

    private int playerId;
    private String playerName;
    private String playerEmail;
    private String playerPhone;
    private String playerPicture;
    private int playerTotalXP;
    private int playerLevel;
    private boolean playerCanDoExam;

    // New calculated data
    private int questsCompleted;
    private int pagesUnlocked;
    private int antidotesMade;

  public void setProfileWithPlayer(Player player){

        this.playerId = player.getPlayerId();
        this.playerName = player.getPlayerName();
        this.playerEmail = player.getPlayerEmail();
        this.playerPhone = player.getPlayerPhone();
        this.playerPicture = player.getPlayerPicture();
        this.playerTotalXP = player.getPlayerTotalXP();
        this.playerLevel = player.getPlayerLevel();
        this.playerCanDoExam = player.isPlayerCanDoExam();
    }
}
