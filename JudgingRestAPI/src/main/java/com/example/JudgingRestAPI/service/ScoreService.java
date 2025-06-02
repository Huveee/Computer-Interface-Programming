package com.example.JudgingRestAPI.service;

import com.example.JudgingRestAPI.model.Judge;
import com.example.JudgingRestAPI.model.Score;
import com.example.JudgingRestAPI.model.Submission;
import com.example.JudgingRestAPI.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public Score createScore(Score score){
        return scoreRepository.save(score);
    }

    public Score updateScore(Score score, Integer scoreId){
        Score oldScore=scoreRepository.findById(scoreId).orElse(null);
        if(oldScore==null){
            return null;
        }
        oldScore.setValue(score.getValue());
        oldScore.setComment(score.getComment());
        oldScore.setJudge(score.getJudge());
        oldScore.setSubmission(score.getSubmission());


        return scoreRepository.save(oldScore);

    }


    public List<Score> getAllScoresbyPhoto(String  imageURL, Integer subId){
        List<Score> scoreList=new ArrayList<>();
        scoreList=scoreRepository.findAll();

        List<Score> newScoreList=new ArrayList<>();

        scoreList.forEach((score -> {
            if(score.getSubmission().getSubId().equals(subId) && score.getSubmission().getImageURL().equals(imageURL)){
                newScoreList.add(score);
            }
        }));

        return newScoreList;

    }


    public List<String> getAllPhotosbyJudge(String imageURL, Integer judgeId) {
        List<Score> scoreList = scoreRepository.findAll();
        List<String> photoList = new ArrayList<>();

        scoreList.forEach(score -> {
            if (score.getJudge().getJudgeId().equals(judgeId) &&
                    score.getSubmission().getImageURL().equals(imageURL)) {
                photoList.add(score.getSubmission().getImageURL());
            }
        });

        return photoList;
    }


    public List<Submission> getLeaderboard(int top) {
        List<Score> scores = scoreRepository.findAll(); // Get all scores

        // Calculate average scores for each submission
        Map<Submission, Double> avgScores = scores.stream()
                .collect(Collectors.groupingBy(
                        Score::getSubmission,
                        Collectors.averagingInt(Score::getValue)
                ));

        // Sort by average score and get the top N submissions
        return avgScores.entrySet().stream()
                .sorted(Map.Entry.<Submission, Double>comparingByValue().reversed()) // Sort descending
                .limit(top) // Get top N
                .map(Map.Entry::getKey) // Extract only Submission objects
                .toList();
    }








}
