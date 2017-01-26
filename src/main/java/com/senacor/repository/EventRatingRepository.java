package com.senacor.repository;

import com.senacor.model.EventRating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by saba on 26.01.17.
 */
@Repository
public interface EventRatingRepository extends MongoRepository<EventRating, UUID> {
    public EventRating findByEventIdAndUserId(String eventId, String userId);
}
