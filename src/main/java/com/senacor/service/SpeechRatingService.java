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
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Marynasuprun on 10.01.2017.
 */

@Service
public class SpeechRatingService {

    private SpeechRatingRepository speechRatingRepository;
    private NaturalPersonRepository naturalPersonRepository;
    private SpeechRepository speechRepository;


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
            //speechRepository.save(speech);
        }

        NaturalPerson naturalPerson = naturalPersonRepository.findByUserId(userId);
        SpeechRating savedRating = speechRatingRepository.findByNaturalPersonAndSpeech(naturalPerson, speech);
        Link selflink = linkTo(SpeechRatingController.class).slash(naturalPerson.getUserId() + "/"
                + savedRating.getSpeechRatingId().toString()).withSelfRel();
        savedRating.add(selflink);
        return savedRating;
    }

    public void addRating(int speechRating, String speechId, String userId) {
        System.out.println("in speechrating service... trying to put new value");
        NaturalPerson naturalPerson = naturalPersonRepository.findByUserId(userId);
        Speech speech = speechRepository.findOne(speechId);
        SpeechRating savedRating = speechRatingRepository.findByNaturalPersonAndSpeech(naturalPerson, speech);
        savedRating.setRating(speechRating);
        //savedRating.setTimestamp(speechRating.getTimestamp());
        speechRatingRepository.save(savedRating);
    }
}
