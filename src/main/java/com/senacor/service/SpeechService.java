package com.senacor.service;

import com.senacor.model.Speech;
import com.senacor.repository.SpeechRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by saba on 16.01.17.
 */
@Service
public class SpeechService {

    SpeechRepository speechRepository;

    @Autowired
    public SpeechService(SpeechRepository speechRepository) {
        this.speechRepository = speechRepository;
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
        speechRepository.delete(speechId);
    }
}
