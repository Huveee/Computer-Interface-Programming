package com.example.JudgingRestAPI.service;

import com.example.JudgingRestAPI.model.Submission;
import com.example.JudgingRestAPI.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;


    public Submission createSubmission(Submission submission){
        return submissionRepository.save(submission);
    }

    public Submission getSubmission(Integer subId){
        return submissionRepository.findById(subId).orElse(null);

    }

    public Submission updateSubmission(Submission submission,Integer subId){
        Submission oldSubmission=submissionRepository.findById(subId).orElse(null);
        if(oldSubmission==null){
            return null;
        }
        oldSubmission.setCategory(submission.getCategory());
        oldSubmission.setTitle(submission.getTitle());
        oldSubmission.setPhotographerName(submission.getPhotographerName());
        oldSubmission.setImageURL(submission.getImageURL());
        oldSubmission.setUploadedAt(submission.getUploadedAt());

        return submissionRepository.save(oldSubmission);

    }

    public void deleteSubmission(Integer id){
        submissionRepository.deleteById(id);
    }




}
