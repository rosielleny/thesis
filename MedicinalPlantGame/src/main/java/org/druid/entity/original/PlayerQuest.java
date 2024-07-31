package org.druid.entity.original;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.druid.entity.original.key.PlayerQuestKey;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerQuest {

    @EmbeddedId
    private PlayerQuestKey playerQuestKey;

    private String questStatus; // Will be Active, Inactive, or Complete
    private String questStage; // Will be "Beginning", "PlantFound", or "AntidoteMade"
}
