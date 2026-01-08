package com.runner.web.controller;

import com.runner.web.dto.EventDto;
import com.runner.web.models.Event;
import com.runner.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {
    private EventService eventService;

    @Autowired
    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @GetMapping("/event/{clubId}/new")
    public String createEvent(@PathVariable("clubId") Long clubId, Model model){
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "event-create";
    }

    @GetMapping("/events")
    public String findAllEvents(Model model){
        List<EventDto> event = eventService.findAllEvents();
        model.addAttribute("events", event);
        return "event-list";
    }

    @PostMapping("/event/{clubId}")
    public String saveEvent(@PathVariable("clubId") Long clubId,
                            @ModelAttribute("event")EventDto eventDto,
                            Model model){
        eventService.createEvent(clubId, eventDto);
        return"redirect:/club/" + clubId;//this will take you to the club detail page
    }
}
