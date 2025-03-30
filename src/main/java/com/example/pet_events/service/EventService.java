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

    // Metodo para registrar un nuevo evento (estaba implementado en un POST pero no se usara en esta entrega)
    public Event createEvent(Event event) {
        validateEvent(event); // Primero valida los campos del evento
        events.add(event); // Luego agrega el evento a la lista de eventos
        return event;
    }

    // Este metodo obtiene todos los eventos registrados
    public List<Event> getAllEvents() {
        return events;
    }

    // Este metodo busca un evento por ID
    public Event getEventById(String id) {
        return events.stream()
                .filter(event -> event.getId().equals(id)) // Filtra los eventos por ID
                .findFirst() // Obtiene el primero que tenga la ID indicada
                .orElseThrow(() -> new EventNotFoundException("Evento no encontrado con ID: " + id)); // Si no lo encuentra lanza la excepción
    }

    // Inscribir un participante en un evento (lo habia implementado en un POST pero no se usara en esta entrega)
    public Event enrollParticipant(String eventId, Participant participant) {
        Event event = getEventById(eventId); // Busca el evento por ID
        event.addParticipant(participant);  // Agrega el participante al evento encontrado
        return event;
    }

    // Este Metodo valida los campos del evento, en este caso solo valide que no sean nulos o vacíos
    private void validateEvent(Event event) {
        if (event.getName() == null || event.getName().isEmpty()) {
            throw new IllegalArgumentException("El nombre del evento es obligatorio.");
        }
        if (event.getDescription() == null || event.getDescription().isEmpty()) {
            throw new IllegalArgumentException("La descripción del evento es obligatoria.");
        }
        if (event.getDate() == null || event.getDate().isEmpty()) {
            throw new IllegalArgumentException("La fecha del evento es obligatoria.");
        }
        if (event.getLocation() == null || event.getLocation().isEmpty()) {
            throw new IllegalArgumentException("La ubicación del evento es obligatoria.");
        }
    }

    // Metodo para obtener los participantes de un evento en particular
    public List<Participant> getParticipantsByEventId(String eventId) {
        Event event = getEventById(eventId); // Reutiliza el método existente para obtener el evento
        return event.getParticipants(); // Devuelve la lista de participantes del evento
    }

    // Función para eliminar un participante de un evento (estaba implementado en un DELETE pero no se usara en esta entrega)
    public Event removeParticipant(String eventId, String participantId) {
        Event event = getEventById(eventId);
        boolean removed = event.getParticipants().removeIf(participant -> participant.getId().equals(participantId));
        if (!removed) {
            throw new ParticipantNotFoundException("Participante no encontrado con ID: " + participantId);
        }
        return event;
    }
}