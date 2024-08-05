package org.plant.entity;

import jakarta.persistence.Column;
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
    @Column(columnDefinition = "TEXT")
    private String medicinalInfo;
    @Column(columnDefinition = "TEXT")
    private String culturalInfo;
    @Column(columnDefinition = "TEXT")
    private String ecosystemInfo;
    @Column(columnDefinition = "TEXT")
    private String scientificInfo;
    @Column(columnDefinition = "TEXT")
    private String additionalInfo;
}
