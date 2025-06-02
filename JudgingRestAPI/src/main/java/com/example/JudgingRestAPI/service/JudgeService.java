package com.example.JudgingRestAPI.service;

import com.example.JudgingRestAPI.model.Judge;
import com.example.JudgingRestAPI.repository.JudgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JudgeService {

    @Autowired
    private JudgeRepository judgeRepository;

    public Judge createJudge(Judge judge){
        return judgeRepository.save(judge);
    }

    public Judge getJudge(Integer judgeId){
        return judgeRepository.findById(judgeId).orElse(null);

    }

    public Judge updateJudge(Judge judge,Integer judgeId){
        Judge oldJudge=judgeRepository.findById(judgeId).orElse(null);
        if(oldJudge==null){
            return null;
        }
        oldJudge.setFirstName(judge.getFirstName());
        oldJudge.setLastName(judge.getLastName());
        oldJudge.setExpertise(judge.getExpertise());


        return judgeRepository.save(oldJudge);

    }

    public void deleteJudge(Integer judgeId){
        judgeRepository.deleteById(judgeId);
    }
}
