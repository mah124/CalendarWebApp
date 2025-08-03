package com.calendar.calendarapp.controller;

import com.calendar.calendarapp.model.Event;
import com.calendar.calendarapp.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/{userId}")
    public List<Event> getEvents(@PathVariable Long userId) {
        return eventService.getEventsByUserId(userId);
    }

    @PostMapping("/create")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @DeleteMapping("/delete/{eventId}")
    public void deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
    }
}
