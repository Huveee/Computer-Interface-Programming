package com.example.JudgingRestAPI.repository;

import com.example.JudgingRestAPI.model.Judge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JudgeRepository extends JpaRepository<Judge,Integer> {
}
