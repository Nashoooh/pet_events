package com.example.pet_events.service;

import com.example.pet_events.model.Participante;
import java.util.List;

public interface ParticipanteService {
    List<Participante> getAllParticipantes();
    Participante getParticipanteById(Long id);
    Participante createParticipante(Participante participante);
    Participante updateParticipante(Long id, Participante participante);
    List<Participante> getParticipantByEvent(Long eventId);
    void deleteParticipante(Long id);
}
