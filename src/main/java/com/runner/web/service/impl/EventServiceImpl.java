package com.runner.web.service.impl;

import com.runner.web.dto.EventDto;
import com.runner.web.models.Club;
import com.runner.web.models.Event;
import com.runner.web.repo.ClubRepo;
import com.runner.web.repo.EventRepo;
import com.runner.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.runner.web.mapper.EventMapper.mapToEvent;

@Service
public class EventServiceImpl implements EventService {
    private EventRepo eventRepo;
    private ClubRepo clubRepo;

    @Autowired
    public EventServiceImpl(EventRepo eventRepo, ClubRepo clubRepo){
        this.eventRepo = eventRepo;
        this.clubRepo = clubRepo;
    }
    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepo.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepo.save(event);
    }
}
