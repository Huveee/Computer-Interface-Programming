package com.example.JudgingRestAPI.repository;

import com.example.JudgingRestAPI.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score,Integer> {
    List<Score> findByOrderBySubmissionAsc();


}
