package org.game.service;

import org.game.dao.RevisionDao;
import org.game.entity.QuestionTemplate;
import org.game.entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevisionServiceImpl implements RevisionService {

    @Autowired
    private RevisionDao revisionDao;

    public List<QuestionTemplate> getAllQuestions() {
        return revisionDao.findAll();
    }

    public Quiz getQuizById(int quizId) {
        return revisionDao.findQuizById(quizId).orElse(null);
    }
}
