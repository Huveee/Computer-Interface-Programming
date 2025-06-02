package com.example.JudgingRestAPI.repository;

import com.example.JudgingRestAPI.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission,Integer> {
}
