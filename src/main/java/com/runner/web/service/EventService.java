package com.runner.web.service;

import com.runner.web.dto.EventDto;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);
}
