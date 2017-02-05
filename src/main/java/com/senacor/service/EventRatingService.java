package com.senacor.service;

import com.senacor.controller.EventRatingController;
import com.senacor.model.EventRating;
import com.senacor.repository.EventRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by saba on 26.01.17.
 */
@Service
public class EventRatingService {

    EventRatingRepository eventRatingRepository;

    @Autowired
    public EventRatingService(EventRatingRepository eventRatingRepository) {
        this.eventRatingRepository = eventRatingRepository;
    }


    public EventRating getRating(String eventId, String userId) {
        EventRating savedRating = eventRatingRepository.findByEventIdAndUserId(eventId, userId);
        if (savedRating != null) {
            Link selflink = linkTo(EventRatingController.class).slash(savedRating.getEventRatingId().toString()).withSelfRel();
            savedRating.add(selflink);
        }
        return savedRating;
    }

    public EventRating addRating(EventRating eventRating) {
        eventRatingRepository.save(eventRating);
        return eventRating;
    }

    public void editRating(EventRating eventRating) {
        eventRatingRepository.save(eventRating);
    }
}
