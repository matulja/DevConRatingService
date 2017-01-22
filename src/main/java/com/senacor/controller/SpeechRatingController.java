package com.senacor.controller;

import com.senacor.model.SpeechRating;
import com.senacor.service.SpeechRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Marynasuprun on 10.01.2017.
 */
@RestController
@RequestMapping("/rating")
public class SpeechRatingController {

    SpeechRatingService speechRatingService;


    @Autowired
    public SpeechRatingController(SpeechRatingService speechRatingService) {
        this.speechRatingService = speechRatingService;
    }

    @RequestMapping(value = "/{speechId}/{userId}", method = RequestMethod.GET)
    public ResponseEntity<SpeechRating> getSpeechRating(@PathVariable("speechId") String speechId, @PathVariable("userId") String userId) {
        System.out.println("in get speechrating controller");
        SpeechRating speechRating = speechRatingService.getRating(userId, speechId);
        System.out.println("speech rating: " + speechRating.getRating() + " for userId " + userId);
        return new ResponseEntity<SpeechRating>(speechRating, HttpStatus.OK);
    }



    @RequestMapping(value = "/{speechId}/{userId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rateSpeech(@PathVariable("speechId") String speechId, @PathVariable("userId") String userId,
                           @RequestBody SpeechRating speechRating) {

        speechRatingService.addRating(speechRating, speechId, userId);
    }

    }
