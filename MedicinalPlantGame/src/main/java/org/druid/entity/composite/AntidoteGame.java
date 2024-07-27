package org.druid.entity.composite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.druid.entity.original.ActionType;
import org.druid.entity.original.Plant;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AntidoteGame {

    private int antidoteId;
    private String antidoteName;
    private String antidotePicture;
    private String antidoteDescription;

    private List<Plant> antidotePlants;
    private List<ActionType> antidoteActions;
}
