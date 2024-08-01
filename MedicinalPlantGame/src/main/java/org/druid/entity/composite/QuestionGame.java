package org.druid.entity.composite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.druid.entity.original.QuestionTemplate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionGame {


    private int questionId;
    private String questionCategory; // Eg. Identification or Medicine
    private String questionSubjectType; // The information in the question which will vary based on plant, either the plant name, a picture of the plant, or the ailment treated will be in the question
    private String questionAnswerType; // Either plant name, plant picture, or ailment
    private String questionText; // The question text itself

    private String answer; // The specific answer of this question
    private List<String> wrongAnswers;

    public void setQuestionGameAttributesFromQuestionTemplate(QuestionTemplate questionTemplate){
        this.questionId = questionTemplate.getQuestionId();
        this.questionCategory = questionTemplate.getQuestionCategory();
        this.questionSubjectType = questionTemplate.getQuestionSubject();
        this.questionAnswerType = questionTemplate.getQuestionAnswer();
        this.questionText = questionTemplate.getQuestionText();
    }
}
