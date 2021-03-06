package com.senacor.controller;

import com.senacor.model.SpeechRating;
import com.senacor.service.SpeechRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


/**
 * Created by Marynasuprun on 10.01.2017.
 */
@RestController
@RequestMapping("/rating/speeches")
public class SpeechRatingController {

    private SpeechRatingService speechRatingService;


    @Autowired
    public SpeechRatingController(SpeechRatingService speechRatingService) {
        this.speechRatingService = speechRatingService;
    }

    @RequestMapping(value = "/{speechId}/{userId}", method = RequestMethod.GET)
    public ResponseEntity<SpeechRating> getSpeechRating(@PathVariable("speechId") String speechId, @PathVariable("userId") String userId) {
        SpeechRating speechRating = speechRatingService.getRating(speechId, userId);
        System.out.println("received user ID: " + userId);
        System.out.println("received speech ID: " + speechId);
        if (speechRating != null) {
            System.out.println("speechrating found");
            return new ResponseEntity<>(speechRating, HttpStatus.OK);
        }else{
            return new ResponseEntity<SpeechRating>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{userId}/{speechId}/add", method = RequestMethod.POST)
    public ResponseEntity<SpeechRating> rateSpeech(@PathVariable("speechId") String speechId, @PathVariable("userId") String userId,
                           @RequestBody SpeechRating speechRating) {
        SpeechRating newRating = speechRatingService.addRating(speechRating);
        return new ResponseEntity<SpeechRating>(newRating, HttpStatus.CREATED);

    }


    @RequestMapping(value = "/{speechRatingId}/edit", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rateSpeech(@PathVariable("speechRatingId") UUID speechRatingId,
                           @RequestBody SpeechRating speechRating) {
        speechRating.setSpeechRatingId(speechRatingId);
        speechRatingService.editRating(speechRating);
    }

    }
