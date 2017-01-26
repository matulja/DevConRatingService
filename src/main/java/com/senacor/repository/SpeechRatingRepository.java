package com.senacor.repository;

import com.senacor.model.SpeechRating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Created by Marynasuprun on 10.01.2017.
 */
@Repository
public interface SpeechRatingRepository extends MongoRepository<SpeechRating, UUID> {

    // SpeechRating findByNaturalPersonAndSpeech(NaturalPerson naturalPerson, Speech speech);

    //List<SpeechRating> findBySpeech(Speech speech);

    SpeechRating findBySpeechIdAndUserId(String speechId, String userId);

    List<SpeechRating> findBySpeechId(String speechId);

}
