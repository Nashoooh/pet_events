package com.example.pet_events.controller;

import com.example.pet_events.exception.EventNotFoundException;
import com.example.pet_events.exception.ParticipantNotFoundException;
import com.example.pet_events.model.Event;
import com.example.pet_events.model.Participant;
import com.example.pet_events.service.EventService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.ok(createdEvent);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        try {
            Event event = eventService.getEventById(id);
            return ResponseEntity.ok(event);
        } catch (EventNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Inscribir un participante en un evento
    @PostMapping("/{eventId}/participants")
    public ResponseEntity<Event> enrollParticipant(
            @PathVariable String eventId,
            @RequestBody Participant participant) {
        try {
            Event event = eventService.enrollParticipant(eventId, participant);
            return ResponseEntity.ok(event);
        } catch (EventNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{eventId}/participants")
    public ResponseEntity<List<Participant>> getParticipantsByEventId(@PathVariable String eventId) {
        try {
            Event event = eventService.getEventById(eventId);
            return ResponseEntity.ok(event.getParticipants());
        } catch (EventNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{eventId}/participants/{participantId}")
    public ResponseEntity<Event> deleteParticipant(
            @PathVariable String eventId,
            @PathVariable String participantId) {
        try {
            Event event = eventService.removeParticipant(eventId, participantId);
            return ResponseEntity.ok(event);
        } catch (EventNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ParticipantNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
