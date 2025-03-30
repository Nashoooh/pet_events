package com.example.pet_events.service;

import com.example.pet_events.exception.EventNotFoundException;
import com.example.pet_events.exception.ParticipantNotFoundException;
import com.example.pet_events.model.Event;
import com.example.pet_events.model.Participant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private final List<Event> events = new ArrayList<>();

    // Registrar un nuevo evento
    public Event createEvent(Event event) {
        validateEvent(event);
        events.add(event);
        return event;
    }

    // Obtener todos los eventos
    public List<Event> getAllEvents() {
        return events;
    }

    // Obtener un evento por ID
    public Event getEventById(String id) {
        return events.stream()
                .filter(event -> event.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EventNotFoundException("Evento no encontrado con ID: " + id));
    }

    // Inscribir un participante en un evento
    public Event enrollParticipant(String eventId, Participant participant) {
        Event event = getEventById(eventId);
        event.addParticipant(participant);
        return event;
    }

    // Validar campos del evento
    private void validateEvent(Event event) {
        if (event.getName() == null || event.getName().isEmpty()) {
            throw new IllegalArgumentException("El nombre del evento es obligatorio.");
        }
        if (event.getDate() == null || event.getDate().isEmpty()) {
            throw new IllegalArgumentException("La fecha del evento es obligatoria.");
        }
    }

    public Event removeParticipant(String eventId, String participantId) {
        Event event = getEventById(eventId);
        boolean removed = event.getParticipants().removeIf(participant -> participant.getId().equals(participantId));
        if (!removed) {
            throw new ParticipantNotFoundException("Participante no encontrado con ID: " + participantId);
        }
        return event;
    }
}