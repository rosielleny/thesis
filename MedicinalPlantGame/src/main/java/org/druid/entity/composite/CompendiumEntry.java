package org.druid.entity.composite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.druid.entity.original.CompendiumPage;
import org.druid.entity.original.Plant;

import java.util.Arrays;
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

    public void setCompendiumEntryWithCompendiumPageAndPlant(CompendiumPage compendiumPage, Plant plant){

        this.plantId = plant.getPlantId();
        this.plantName = plant.getPlantName();
        this.defaultPlantPicture = plant.getDefaultPicture();
        this.treatmentFor = plant.getTreatmentFor();
        this.season = plant.getSeason();
        this.uniqueFeatures = Arrays.asList(plant.getUniqueFeature1(), plant.getUniqueFeature2(), plant.getUniqueFeature3());

        this.medicinalInfo = compendiumPage.getMedicinalInfo();
        this.culturalInfo = compendiumPage.getCulturalInfo();
        this.ecosystemInfo = compendiumPage.getEcosystemInfo();
        this.scientificInfo = compendiumPage.getScientificInfo();
        this.additionalInfo = compendiumPage.getAdditionalInfo();


    }

}

