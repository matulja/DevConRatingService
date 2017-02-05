package com.senacor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.ResourceSupport;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.UUID;


/**
 * Created by Marynasuprun on 10.01.2017.
 */
@Document(collection = "speechRating")
public class SpeechRating extends ResourceSupport implements Serializable {

    @Id
    private UUID speechRatingId;
    private String userId;
    private String speechId;
    private LocalTime timestamp;
    private Integer rating;
    private String comment;

    public SpeechRating() {
        speechRatingId = UUID.randomUUID();
    }

    public UUID getSpeechRatingId() {
        return speechRatingId;
    }

    public void setSpeechRatingId(UUID speechRatingId) {
        this.speechRatingId = speechRatingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSpeechId() {
        return speechId;
    }

    public void setSpeechId(String speechId) {
        this.speechId = speechId;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
