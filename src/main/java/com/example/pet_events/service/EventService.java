package com.example.pet_events.service;

import com.example.pet_events.exception.EventNotFoundException;
import com.example.pet_events.model.Event;
import com.example.pet_events.model.Participant;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class EventService {
    private final List<Event> events = new ArrayList<>();
    private final List<Participant> participantList = new ArrayList<>();

    @PostConstruct
    public void init() {
        
        // Crear participantes en una lista
        participantList.add(new Participant("1", "Juan Pérez", "Firulais", "Perro", 3));
        participantList.add(new Participant("2", "María López", "Michi", "Gato", 2));
        participantList.add(new Participant("3", "Carlos Sánchez", "Rex", "Perro", 5));
        participantList.add(new Participant("4", "Ana Gómez", "Nemo", "Pez", 1));
        participantList.add(new Participant("5", "Luis Torres", "Luna", "Gato", 4));
        participantList.add(new Participant("6", "Sofía Díaz", "Rocky", "Perro", 6));
        participantList.add(new Participant("7", "Pedro Ramírez", "Kiwi", "Ave", 2));
        participantList.add(new Participant("8", "Laura Castillo", "Max", "Perro", 7));
        participantList.add(new Participant("9", "Diego Fernández", "Bella", "Conejo", 3));
    
        // Crear eventos y asociar participantes
        Event events1 = new Event("1", "Adopción de Mascotas", "Evento de adopción de mascotas", "2023-10-15", "Parque Bicentenario");
        events1.addParticipant(participantList.get(0));
        events1.addParticipant(participantList.get(1));
        events1.addParticipant(participantList.get(2));
    
        Event events2 = new Event("2", "Tenencia Responsable", "Charla sobre tenencia responsable de mascotas", "2023-10-20", "Centro Cultural La Moneda");
        events2.addParticipant(participantList.get(3));
        events2.addParticipant(participantList.get(4));
        events2.addParticipant(participantList.get(5));
    
        Event events3 = new Event("3", "Cuidados de Salud", "Taller sobre cuidados de salud para mascotas", "2023-10-25", "Estadio Nacional");
        events3.addParticipant(participantList.get(6));
        events3.addParticipant(participantList.get(7));
        events3.addParticipant(participantList.get(8));
    
        // Agregar eventos a la lista
        events.add(events1);
        events.add(events2);
        events.add(events3);
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

    // Metodo para obtener los participantes de un evento en particular
    public List<Participant> getParticipantsByEventId(String eventId) {
        Event event = getEventById(eventId); // Reutiliza el método existente para obtener el evento
        return event.getParticipants(); // Devuelve la lista de participantes del evento
    }

}