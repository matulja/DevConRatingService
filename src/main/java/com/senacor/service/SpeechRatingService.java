package com.senacor.service;


import com.senacor.model.NaturalPerson;
import com.senacor.model.Speech;
import com.senacor.model.SpeechRating;
import com.senacor.repository.NaturalPersonRepository;
import com.senacor.repository.SpeechRatingRepository;
import com.senacor.repository.SpeechRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

/**
 * Created by Marynasuprun on 10.01.2017.
 */

@Service
public class SpeechRatingService {

    SpeechRatingRepository speechRatingRepository;
    NaturalPersonRepository naturalPersonRepository;
    SpeechRepository speechRepository;



    @Autowired
    public SpeechRatingService(SpeechRatingRepository speechRatingRepository, NaturalPersonRepository naturalPersonRepository,
                               SpeechRepository speechRepository) {
        this.speechRatingRepository = speechRatingRepository;
        this.naturalPersonRepository = naturalPersonRepository;
        this.speechRepository = speechRepository;
    }

    public SpeechRating getRating(String userId, String speechId) {
        //first check if speech already in speechRepository
        Speech speech = speechRepository.findOne(speechId);
        if (speech == null) {
            //TODO if speech not yet in repository, get speech from eventservice
            //speech = ...
            //save speech in own speech repo
            speechRepository.save(speech);
        }

        //natural person from naturalPerson repository - if naturalPerson == null omitted,
        // as addition/deletion etc. of users not forseen at the moment
        NaturalPerson naturalPerson = naturalPersonRepository.findByUserId(userId);
        //get speechRating for the speech of the user
        SpeechRating speechRating = speechRatingRepository.findByNaturalPersonAndSpeech(naturalPerson, speech);
        //if not rating exists yet, create a new one and save it
        if (speechRating == null) {
            speechRating = new SpeechRating();
            speechRating.setRating(0);
            speechRating.setComment("");
            speechRatingRepository.save(speechRating);
        }

        return speechRating;
    }

    public void addRating(SpeechRating speechRating, String speechId, String userId) {
        NaturalPerson naturalPerson = naturalPersonRepository.findByUserId(userId);
        Speech speech = speechRepository.findOne(speechId);
        SpeechRating savedRating = speechRatingRepository.findByNaturalPersonAndSpeech(naturalPerson, speech);
        savedRating.setRating(speechRating.getRating());
        savedRating.setComment(speechRating.getComment());
        savedRating.setTimestamp(speechRating.getTimestamp());
        speechRatingRepository.save(savedRating);
    }
}
