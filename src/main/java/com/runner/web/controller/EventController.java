package com.runner.web.controller;

import com.runner.web.dto.ClubDto;
import com.runner.web.dto.EventDto;
import com.runner.web.models.Event;
import com.runner.web.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/events/{eventId}")
    public String viewEvent(@PathVariable("eventId") Long eventId, Model model){
        EventDto eventDto = eventService.findByEventId(eventId);
        model.addAttribute("event", eventDto);
        return "event-detail";
    }

    @PostMapping("/event/{clubId}")
    public String saveEvent(@PathVariable("clubId") Long clubId,
                            @ModelAttribute("event")EventDto eventDto,
                            Model model){
        eventService.createEvent(clubId, eventDto);
        return"redirect:/club/" + clubId;//this will take you to the club detail page
    }

    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable Long eventId, Model model){
        EventDto events = eventService.findByEventId(eventId);
        model.addAttribute("event", events);
        return "event-edit";
    }

    @PostMapping("/events/{eventId}/edit")
    public String updateEvent(@PathVariable Long eventId,
                             @Valid @ModelAttribute("event") EventDto events,
                             BindingResult result,
                              Model model){ //the purpose of the model is to get some extra validation
        if(result.hasErrors()){
            model.addAttribute("event", events);
            return "event-edit";
        }
        EventDto eventDto = eventService.findByEventId(eventId);//this is lazy loading
        events.setId(eventId);
        events.setClub(eventDto.getClub());//this is under lazy loading. Find out how exactly it works
        eventService.updateEvent(events);
        return "redirect:/events";
    }

    @GetMapping("events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId){
        eventService.deleteEvent(eventId);
        return "redirect:/events";
    }
}
