package com.senacor.repository;

import com.senacor.model.Speech;
import com.senacor.model.SpeechRating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by saba on 14.01.17.
 */
@Repository
public interface SpeechRepository extends MongoRepository<Speech, String> {

    Speech findBySpeechId(String speechId);

}
