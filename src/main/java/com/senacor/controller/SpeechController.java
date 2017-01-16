package com.senacor.controller;

import com.senacor.model.Speech;
import com.senacor.model.SpeechRating;
import com.senacor.service.SpeechRatingService;
import com.senacor.service.SpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by saba on 16.01.17.
 */
@RestController
@RequestMapping("/dataUpdate")
public class SpeechController {

    SpeechService speechService;

    @Autowired
    public SpeechController(SpeechService speechService) {
        this.speechService = speechService;
    }

    @RequestMapping(value = "/createSpeech", method = RequestMethod.POST)
    public ResponseEntity<Speech> addSpeech(@RequestBody Speech speech) {
        System.out.println("in add speechcontroller");
        speechService.createSpeech(speech);
        return new ResponseEntity<Speech>(speech, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/editSpeech/{speechId}", method = RequestMethod.PUT)
    public ResponseEntity<Speech> editSpeech(@RequestBody Speech speech) {
        System.out.println("in edit speechcontroller");
        speechService.updateSpeech(speech);
        return new ResponseEntity<Speech>(speech, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/deleteSpeech/{speechId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> editSpeech(@PathVariable (value = "speechId") String speechId) {
        System.out.println("in delete speechcontroller");
        speechService.deleteSpeech(speechId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
