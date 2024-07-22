package org.plant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompendiumPage {

    @Id
    private int plantId;
    private String medicinalInfo;
    private String culturalInfo;
    private String ecosystemInfo;
    private String scientificInfo;
    private String additionalInfo;
}
