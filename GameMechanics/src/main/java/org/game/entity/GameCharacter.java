package org.game.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* This entity reflects the GameCharacter database table which holds a character's name
* and picture which will be used for visual novel cutscenes in quests.*/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameCharacter {

    @Id
    private int gameCharacterId;
    private String gameCharacterName;
    private String gameCharacterPicture;
}
