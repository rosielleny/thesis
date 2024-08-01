package org.druid.entity.original;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* Reflects Quiz table, which describes the different types of quiz available*/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

    @Id
    private int quizId;
    private boolean isExam;
    private int questionNumber; // eg. a normal quiz may be 10 questions, an exam may be 20
    private int xpWorth; // The xp the player will be rewarded with
}
