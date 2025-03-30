package com.example.pet_events.controller;

import com.example.pet_events.exception.EventNotFoundException;
import com.example.pet_events.exception.ParticipantNotFoundException; // No se utiliza en esta entrega
import com.example.pet_events.model.Event;
import com.example.pet_events.model.Participant;
import com.example.pet_events.service.EventService;

import org.springframework.http.HttpStatus;  // No se utiliza en esta entrega
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

    // Implemente el Post para crear un evento, pero aun no es solicitado en esta entrega.
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

    @GetMapping("/{eventId}/participants")
    public ResponseEntity<List<Participant>> getParticipantsByEventId(@PathVariable String eventId) {
        try {
            List<Participant> participants = eventService.getParticipantsByEventId(eventId);
            return ResponseEntity.ok(participants);
        } catch (EventNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Implemente el Post para inscribir un participante en un evento, pero aun no es solicitado en esta entrega.
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

    // Implemente el Delete para eliminar un participante de un evento, pero aun no es solicitado en esta entrega.
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
