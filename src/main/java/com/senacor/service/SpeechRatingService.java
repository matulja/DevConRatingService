package com.senacor.service;


import com.senacor.controller.SpeechRatingController;
import com.senacor.model.SpeechRating;
import com.senacor.repository.SpeechRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

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

    public SpeechRating getRating(String speechId, String userId) {

        //first check if speech already in speechRepository
       // Speech speech = speechRepository.findBySpeechId(speechId);
        /*if (speech == null) {
            //TODO if speech not yet in repository, get speech from eventservice and create speechrating for all users
            //speech = ...
            //save speech in own speech repo
            System.out.println("speech does not exist... ");
            //speechRepository.save(speech);
        }*/

        //NaturalPerson naturalPerson = naturalPersonRepository.findByUserId(userId);
        SpeechRating savedRating = speechRatingRepository.findBySpeechIdAndUserId(speechId, userId);
        if (savedRating != null) {
            Link selflink = linkTo(SpeechRatingController.class).slash(savedRating.getSpeechRatingId().toString()).withSelfRel();
            savedRating.add(selflink);
        }
        return savedRating;
    }

    public SpeechRating addRating(SpeechRating speechRating) {
        System.out.println("in speechrating service... trying to put new value");
        speechRatingRepository.save(speechRating);
        //savedRating.setTimestamp(speechRating.getTimestamp());
        return speechRatingRepository.findOne(speechRating.getSpeechRatingId());
    }

    public void editRating(SpeechRating speechRating) {
        speechRatingRepository.save(speechRating);
    }
}
