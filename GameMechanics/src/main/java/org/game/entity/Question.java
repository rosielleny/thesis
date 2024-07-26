package org.game.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* Reflecting the database table "Question" which gives a template for a quiz question to
* be formed. */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    private int questionId;
    private String questionCategory; // Eg. Identification or Medicine
    private String questionSubject; // The information in the question which will vary based on plant, either the plant name, a picture of the plant, or the ailment treated will be in the question
    private String questionAnswer; // Either plant name, plant picture, or ailment
    private String question; // The question text itself
}
