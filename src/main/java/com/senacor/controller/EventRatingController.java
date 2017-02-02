package com.senacor.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.senacor.model.EventRating;
import com.senacor.model.SpeechRating;
import com.senacor.service.EventRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by saba on 26.01.17.
 */
@RestController
@RequestMapping("/rating/events")
public class EventRatingController {

    EventRatingService eventRatingService;

    @Autowired
    public EventRatingController(EventRatingService eventRatingService) {
        this.eventRatingService = eventRatingService;
    }


    @RequestMapping(value = "/{eventId}/{userId}", method = RequestMethod.GET)
    public ResponseEntity<EventRating> getSpeechRating(@PathVariable("eventId") String eventId, @PathVariable("userId") String userId) {
        System.out.println("in get eventrating controller");
        EventRating eventRating = eventRatingService.getRating(eventId, userId);
        if (eventRating != null) {
            return new ResponseEntity<>(eventRating, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{eventId}/{userId}/add", method = RequestMethod.POST)
    public ResponseEntity<EventRating> rateSpeech(@PathVariable("eventId") String eventId, @PathVariable("userId") String userId,
                                                   @RequestBody EventRating eventRating) {
        System.out.println("in post eventrating controller");
        System.out.println("information rating = " + eventRating.getInformationRating());
        eventRating.setEventId(eventId);
        eventRating.setUserId(userId);
        EventRating newRating = eventRatingService.addRating(eventRating);
        return new ResponseEntity<>(newRating, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/{eventRatingId}/edit", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rateEvent(@PathVariable("eventRatingId") UUID eventRatingId,
                           @RequestBody EventRating eventRating) {
        System.out.println("in put eventrating controller");
        eventRating.setEventRatingId(eventRatingId);
        eventRatingService.editRating(eventRating);
    }
}
