package com.senacor.controller;

import com.senacor.service.SpeechRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Marynasuprun on 10.01.2017.
 */
@RestController
@RequestMapping("/speechRating")
public class SpeechRatingController {

    SpeechRatingService speechRatingService;

    @Autowired
    public SpeechRatingController(SpeechRatingService speechRatingService) {
        this.speechRatingService = speechRatingService;
    }

    @RequestMapping(value = "/{speechId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void rateSpeech(@PathVariable("speechId") String speechId, @RequestParam("rating") int individualRating) {

    }

    }
