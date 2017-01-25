package com.senacor.service;

import com.senacor.model.NaturalPerson;
import com.senacor.model.Speech;
import com.senacor.model.SpeechRating;
import com.senacor.repository.NaturalPersonRepository;
import com.senacor.repository.SpeechRatingRepository;
import com.senacor.repository.SpeechRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by saba on 16.01.17.
 */
@Service
public class SpeechService {

    private SpeechRepository speechRepository;
    private SpeechRatingRepository speechRatingRepository;
    private NaturalPersonRepository naturalPersonRepository;

    @Autowired
    public SpeechService(SpeechRepository speechRepository, SpeechRatingRepository speechRatingRepository,
                         NaturalPersonRepository naturalPersonRepository) {
        this.speechRepository = speechRepository;
        this.speechRatingRepository = speechRatingRepository;
        this.naturalPersonRepository = naturalPersonRepository;
    }

    public void createSpeech(Speech speech) {
        System.out.println("saving new speech");
        speechRepository.save(speech);
    }


    public void updateSpeech(Speech speech) {
        System.out.println("saving edited speech");
        speechRepository.save(speech);
    }

    public void deleteSpeech(String speechId) {
        System.out.println("delete speech");
        for (SpeechRating sr : speechRatingRepository.findBySpeechId(speechId)) {
            speechRatingRepository.delete(sr);
        }
        Speech speech = speechRepository.findOne(speechId);
        speechRepository.delete(speech);
    }
}
