package com.example.JudgingRestAPI.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer subId;

    private String title;

    private String category;
    private Date uploadedAt;
    private String photographerName;
    private String imageURL;

    public Submission(String title, String category, Date uploadedAt, String photographerName, String imageURL) {
        this.title = title;
        this.category = category;
        this.uploadedAt = uploadedAt;
        this.photographerName = photographerName;
        this.imageURL = imageURL;
    }

    public Submission (){}

    public Integer getSubId() {
        return subId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Date uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public String getPhotographerName() {
        return photographerName;
    }

    public void setPhotographerName(String photographerName) {
        this.photographerName = photographerName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
