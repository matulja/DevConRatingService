package com.senacor.repository;

import com.senacor.model.SpeechRating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Marynasuprun on 10.01.2017.
 */
@Repository
public interface SpeechRatingRepository extends MongoRepository<SpeechRating, String> {
}
