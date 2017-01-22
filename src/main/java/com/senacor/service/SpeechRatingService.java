package com.senacor.service;


import com.senacor.controller.SpeechRatingController;
import com.senacor.model.NaturalPerson;
import com.senacor.model.Speech;
import com.senacor.model.SpeechRating;
import com.senacor.repository.NaturalPersonRepository;
import com.senacor.repository.SpeechRatingRepository;
import com.senacor.repository.SpeechRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

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
        Speech speech = speechRepository.findBySpeechId(speechId);
        if (speech == null) {
            //TODO if speech not yet in repository, get speech from eventservice and create speechrating for all users
            //speech = ...
            //save speech in own speech repo
            System.out.println("speech does not exist... ");
            speechRepository.save(speech);
        }

        //get all speechRatings for the speech
        List<SpeechRating> speechRatingsForSpeech = speechRatingRepository.findBySpeech(speech);
        System.out.println("no. of all speech ratings found for this speech: " + speechRatingsForSpeech.size());

        //natural person from naturalPerson repository - if naturalPerson == null omitted,
        // as addition/deletion etc. of users not forseen at the moment
        NaturalPerson naturalPerson = naturalPersonRepository.findByUserId(userId);
        System.out.println("natural Person name = " + naturalPerson.getFirstname() + " " + naturalPerson.getLastname());

        SpeechRating speechRating = null;

        for (SpeechRating sr : speechRatingsForSpeech) {
            System.out.println("speechrating for user: " + sr.getNaturalPerson().getFirstname());
            if (sr.getNaturalPerson().getUserId().equals(naturalPerson.getUserId())) {
                speechRating = sr;
                break;
            }

        }
        Link selflink = linkTo(SpeechRatingController.class).slash(naturalPerson.getUserId() + "/"
                + speechRating.getSpeechRatingId().toString()).withSelfRel();
        speechRating.add(selflink);
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
