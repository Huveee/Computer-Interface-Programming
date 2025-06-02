package com.example.JudgingRestAPI.controller;

import com.example.JudgingRestAPI.model.Score;
import com.example.JudgingRestAPI.model.Submission;
import com.example.JudgingRestAPI.service.ScoreService;
import com.example.JudgingRestAPI.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private ScoreService scoreService;


    @PostMapping()
    public ResponseEntity<?> createSubmission(@RequestBody Submission submission){
        try{
            Submission createdSubmission=submissionService.createSubmission(submission);
            return new ResponseEntity<Submission>(createdSubmission,HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Error: " + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("{subId}")
    public ResponseEntity<?> getSubmission(@PathVariable Integer submissionId){
        Submission submission=submissionService.getSubmission(submissionId);
        if(submission==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(submission,HttpStatus.OK);

    }

    @PutMapping("{subId}")
    public ResponseEntity<?> updateSubmission(@RequestBody Submission submission, @PathVariable Integer subId){
        Submission submission1=submissionService.updateSubmission(submission,subId);
        if(submission1==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(submission1,HttpStatus.OK);

    }


    @DeleteMapping("{subId}")
    public ResponseEntity<HttpStatus> deleteSubmission(@PathVariable Integer subId){
        try {
            submissionService.deleteSubmission(subId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{subId}/scores")
    public ResponseEntity<?> getAllScoresbyPhoto(@RequestParam String imageURL, @PathVariable Integer subId){
        List<Score> scoreList=scoreService.getAllScoresbyPhoto (imageURL,subId);
        return new ResponseEntity<>(scoreList,HttpStatus.OK);

    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<Submission>> getLeaderboard(@RequestParam(defaultValue = "20") int top) {
        return ResponseEntity.ok(scoreService.getLeaderboard(top));
    }






















}
