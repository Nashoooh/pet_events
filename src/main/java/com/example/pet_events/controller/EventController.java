package com.example.pet_events.controller;

import com.example.pet_events.exception.EventNotFoundException;
import com.example.pet_events.model.Event;
import com.example.pet_events.model.Participant;
import com.example.pet_events.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService; // Se declara el servicio para utilizar sus métodos

    // Constructor del servicio para utilizar sus métodos
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        if (events.isEmpty()) {
            // Devuelve una lista vacía con código 200 OK en caso de que no haya eventos registrados
            return ResponseEntity.ok(events);
        }
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        Event event = eventService.getEventById(id); // Si no se encuentra, se lanza la excepción
        return ResponseEntity.ok(event); // Devuelve el evento con código 200 OK
    }

    @GetMapping("/{eventId}/participants")
    public ResponseEntity<List<Participant>> getParticipantsByEventId(@PathVariable String eventId) {
        List<Participant> participants = eventService.getParticipantsByEventId(eventId);
        // Devuelve una lista vacía con código 200 OK si no hay participantes
        return ResponseEntity.ok(participants);
    }

}
