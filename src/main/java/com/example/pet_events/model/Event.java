package com.example.pet_events.model;

import java.util.ArrayList;
import java.util.List;


public class Event {
    private String id;
    private String name;
    private String description;
    private String date;
    private String location;
    private List<Participant> participants; // Lista de participantes, un evento puede estar asociado a varios participantes

    public Event(String id, String name, String description, String date, String location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
        this.participants = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Este metodo permite obtener la lista de participantes asociados al evento
    public List<Participant> getParticipants() {
        return participants;
    }

    // Este metodo permite agregar un participante a la lista de participantes asociados al evento
    public void addParticipant(Participant participant) {
        this.participants.add(participant);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
