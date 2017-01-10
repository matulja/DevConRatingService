package com.senacor.service;


import com.senacor.repository.SpeechRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Marynasuprun on 10.01.2017.
 */

@Service
public class SpeechRatingService {

    SpeechRatingRepository speechRatingRepository;

    @Autowired
    public SpeechRatingService(SpeechRatingRepository speechRatingRepository) {
        this.speechRatingRepository = speechRatingRepository;
    }




}
