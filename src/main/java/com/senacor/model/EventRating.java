package com.senacor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.UUID;

/**
 * Created by saba on 26.01.17.
 */
@Document(collection = "eventRating")
public class EventRating extends ResourceSupport implements Serializable{

    //Verpflegung”, “Räume / Location”, “Themenauswahl”, “Informationen im Vorfeld”, “Anfahrt”, vlt. ein Feld für Verbesserungsvorschläge
    @Id
    private UUID eventRatingId;
    private int caterRating;
    private int locationRating;
    private int informationRating;
    private int contentRating;
    private String suggestions;
    private String eventId;
    private String userId;
    private LocalTime timestamp;

    public EventRating() {
        this.eventRatingId = UUID.randomUUID();
    }

    public UUID getEventRatingId() {
        return eventRatingId;
    }

    public void setEventRatingId(UUID eventRatingId) {
        this.eventRatingId = eventRatingId;
    }

    public int getCaterRating() {
        return caterRating;
    }

    public void setCaterRating(int caterRating) {
        this.caterRating = caterRating;
    }

    public int getLocationRating() {
        return locationRating;
    }

    public void setLocationRating(int locationRating) {
        this.locationRating = locationRating;
    }

    public int getInformationRating() {
        return informationRating;
    }

    public void setInformationRating(int informationRating) {
        this.informationRating = informationRating;
    }

    public String getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(String suggestions) {
        this.suggestions = suggestions;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getContentRating() {
        return contentRating;
    }

    public void setContentRating(int contentRating) {
        this.contentRating = contentRating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
