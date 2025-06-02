package com.example.JudgingRestAPI.controller;

import com.example.JudgingRestAPI.model.Judge;
import com.example.JudgingRestAPI.model.Judge;
import com.example.JudgingRestAPI.model.Score;
import com.example.JudgingRestAPI.service.JudgeService;
import com.example.JudgingRestAPI.service.JudgeService;
import com.example.JudgingRestAPI.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/judges")
public class JudgeController {

    @Autowired
    private JudgeService judgeService;

    @Autowired
    private ScoreService scoreService;


    @PostMapping()
    public ResponseEntity<?> createJudge(@RequestBody Judge judge){
        try{
            Judge createdJudge=judgeService.createJudge(judge);
            return new ResponseEntity<Judge>(createdJudge, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Error: " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("{judgeId}")
    public ResponseEntity<?> getJudge(@PathVariable Integer judgeId){
        Judge judge=judgeService.getJudge(judgeId);
        if(judge==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(judge,HttpStatus.OK);

    }

    @PutMapping("{judgeId}")
    public ResponseEntity<?> updateJudgeId(@RequestBody Judge judge, @PathVariable Integer judgeId){
        Judge judge1=judgeService.updateJudge(judge,judgeId);
        if(judge1==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(judge1,HttpStatus.OK);

    }


    @DeleteMapping("{judgeId}")
    public ResponseEntity<HttpStatus> deleteJudge(@PathVariable Integer judgeId){
        try {
            judgeService.deleteJudge(judgeId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{judgeId}/scores")
    public ResponseEntity<?> getAllPhotosbyJudge(@RequestParam String imageURL, @PathVariable Integer judgeId){
        List<Score> scoreList=scoreService.getAllScoresbyPhoto (imageURL,judgeId);
        return new ResponseEntity<>(scoreList,HttpStatus.OK);

    }




}
