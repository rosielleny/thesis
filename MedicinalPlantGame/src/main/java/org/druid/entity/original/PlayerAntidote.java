package org.druid.entity.original;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.player.entity.key.PlayerAntidoteKey;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerAntidote {

    @EmbeddedId
    private PlayerAntidoteKey playerAntidoteKey;

    // private int playerId;
    // private int antidoteId;
    private int numberMade; // Tracks total number crafted in antidote minigame
    private int numberUsed; // Tracks total number handed in for a quest to ensure that a player will have to make another if a second quests asks for the same antidote

}
