package org.druid.entity.composite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompendiumEntry {


    // From Plant table
    private int plantId;
    private String plantName;
    private String defaultPlantPicture;
    private List<String> uniqueFeatures;
    private String treatmentFor;
    private String season;

    // From CompendiumPage table
    private String medicinalInfo;
    private String culturalInfo;
    private String ecosystemInfo;
    private String scientificInfo;
    private String additionalInfo;

    // From PlayerPictures table
    private List<String> playerPictures;

}

