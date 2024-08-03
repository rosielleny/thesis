package org.game.service;

import org.game.dao.QuestionTemplateDao;
import org.game.dao.QuizDao;
import org.game.entity.QuestionTemplate;
import org.game.entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevisionServiceImpl implements RevisionService {

    @Autowired
    private QuestionTemplateDao questionDao;
    @Autowired
    private QuizDao quizDao;

    public List<QuestionTemplate> getAllQuestions() {

        return questionDao.findAll();
    }

    public Quiz getQuizById(int quizId) {

        return quizDao.findQuizById(quizId).orElse(null);
    }

    @Override
    public List<Quiz> getQuizzes() {
        return quizDao.findAll();
    }


}
