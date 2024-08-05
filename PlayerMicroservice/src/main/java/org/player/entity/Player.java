package org.player.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    private int playerId;
    private String playerName;
    private String playerEmail;
    private String playerPhone;
    private String playerPicture;
    private int playerTotalXP;
    private byte playerLevel;
    private boolean playerCanDoExam;
}
