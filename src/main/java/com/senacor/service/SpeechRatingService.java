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


    @Autowired
    public SpeechRatingService(SpeechRatingRepository speechRatingRepository) {
        this.speechRatingRepository = speechRatingRepository;
    }

    public SpeechRating getRating(String speechId, String userId) {
        SpeechRating savedRating = speechRatingRepository.findBySpeechIdAndUserId(speechId, userId);
        if (savedRating != null) {
            Link selflink = linkTo(SpeechRatingController.class).slash(savedRating.getSpeechRatingId().toString()).withSelfRel();
            savedRating.add(selflink);
        }
        return savedRating;
    }

    public SpeechRating addRating(SpeechRating speechRating) {
        speechRatingRepository.save(speechRating);
        return speechRatingRepository.findOne(speechRating.getSpeechRatingId());
    }

    public void editRating(SpeechRating speechRating) {
        speechRatingRepository.save(speechRating);
    }
}
