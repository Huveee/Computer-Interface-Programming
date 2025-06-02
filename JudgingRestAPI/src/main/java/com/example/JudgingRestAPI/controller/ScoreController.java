package com.example.JudgingRestAPI.controller;

import com.example.JudgingRestAPI.model.Judge;
import com.example.JudgingRestAPI.model.Score;
import com.example.JudgingRestAPI.service.JudgeService;
import com.example.JudgingRestAPI.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PostMapping()
    public ResponseEntity<?> createScore(@RequestBody Score score){
        try{
            Score createdScore=scoreService.createScore(score);
            return new ResponseEntity<Score>(createdScore, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Error: " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @PutMapping("{scoreId}")
    public ResponseEntity<?> updateScore(@RequestBody Score score, @PathVariable Integer scoreId){
        Score score1=scoreService.updateScore(score,scoreId);
        if(score1==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(score1,HttpStatus.OK);

    }












}
