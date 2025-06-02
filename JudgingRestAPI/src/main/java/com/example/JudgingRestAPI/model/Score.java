package com.example.JudgingRestAPI.model;

import jakarta.persistence.*;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer scoreId;

    private Integer scoreValue;

    private String comment;

    @ManyToOne
    private Judge judge;

    @ManyToOne
    private Submission submission;


    public Score(Integer scoreValue, String comment, Judge judge, Submission submission) {
        this.scoreValue = scoreValue;
        this.comment = comment;
        this.judge = judge;
        this.submission = submission;
    }

    public Score(){}

    public Integer getScoreId() {
        return scoreId;
    }

    public Integer getValue() {
        return scoreValue;
    }


    public void setValue(Integer scoreValue) {
        if (scoreValue < 1 || scoreValue > 10) {
            throw new IllegalArgumentException("Score value must be between 1 and 10");
        }
        this.scoreValue = scoreValue;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Judge getJudge() {
        return judge;
    }

    public void setJudge(Judge judge) {
        this.judge = judge;
    }

    public Submission getSubmission() {
        return submission;
    }

    public void setSubmission(Submission submission) {
        this.submission = submission;
    }
}
