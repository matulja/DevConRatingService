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
        EventRating eventRating = eventRatingRepository.findByEventIdAndUserId(eventId, userId);
        if (eventRating != null) {
            System.out.println("eventrating is not null");
            Link selflink = linkTo(EventRatingController.class).slash(eventRating.getEventRatingId().toString()).withSelfRel();
            eventRating.add(selflink);
        }
        return eventRating;
    }

    public EventRating addRating(EventRating eventRating) {
        System.out.println("in eventrating service... trying to put new value");
        eventRatingRepository.save(eventRating);
        System.out.println("this is the eventRating: " + eventRating.toString());
        return eventRating;
    }

    public void editRating(EventRating eventRating) {
        eventRatingRepository.save(eventRating);
    }
}
