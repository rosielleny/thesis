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
public class GameCharacter {

    @Id
    private int gameCharacterId;
    private String gameCharacterName;
    private String gameCharacterPicture;
}
