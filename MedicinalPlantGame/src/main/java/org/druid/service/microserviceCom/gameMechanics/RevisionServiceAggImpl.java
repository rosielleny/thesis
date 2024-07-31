package org.druid.service.microserviceCom.gameMechanics;

import org.druid.entity.original.Antidote;
import org.druid.entity.original.Quest;
import org.druid.entity.original.Question;
import org.druid.entity.original.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RevisionServiceAggImpl implements RevisionServiceAgg {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HttpHeaders httpHeaders;

    String startUrl = "http://localhost:7002/revision/";

    // Getting templates which will be used to make questions
    @Override
    public List<Question> getAllQuestions() {

        String url = startUrl + "questions";

        ResponseEntity<List<Question>> response =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Question>>() {});

        if(response.getStatusCode() == HttpStatus.OK){
            return response.getBody();
        }
        else{
            return null;
        }
    }

    // Getting the type of quiz that is going to be made
    @Override
    public Quiz getQuizById(int quizId) {

        String url = startUrl + "quiz/" + quizId;
        return restTemplate.getForObject(url, Quiz.class);
    }
}
