package com.example.pet_events.service;

import com.example.pet_events.model.Participante;
import com.example.pet_events.repository.EventoRepository;
import com.example.pet_events.repository.ParticipanteRepository;
import com.example.pet_events.model.Evento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParticipanteServiceImpl implements ParticipanteService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Override
    public List<Participante> getAllParticipantes() {
        // Recupera todos los participantes de la base de datos
        return participanteRepository.findAll();
    }

    @Override
    public Participante getParticipanteById(Long id) {
        // Busca un participante por su ID
        return participanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participante no encontrado con ID: " + id));
    }

    @Override
    public Participante createParticipante(Participante participante) {

        Evento evento = participante.getEvento();

        if (evento != null) {
            // Verifica si el evento existe
            Evento existingEvento = eventoRepository.findById(evento.getId())
                    .orElseThrow(() -> new RuntimeException("Evento no encontrado con ID: " + evento.getId()));
            participante.setEvento(existingEvento);
        }

        return participanteRepository.save(participante);
    }

    @Override
    public Participante updateParticipante(Long id, Participante participante) {
        // Actualiza un participante existente
        Participante existingParticipante = participanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participante no encontrado con ID: " + id));

        // Actualiza los campos del participante
        existingParticipante.setName(participante.getName());
        existingParticipante.setPetName(participante.getPetName());
        existingParticipante.setPetAge(participante.getPetAge());
        existingParticipante.setPetType(participante.getPetType());
        return participanteRepository.save(existingParticipante);
    }

    @Override
    public void deleteParticipante(Long id) {
        // Elimina un participante por su ID
        Participante participante = participanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participante no encontrado con ID: " + id));
        participanteRepository.delete(participante);
    }

    @Override
    public List<Participante> getParticipantByEvent(Long eventId) {
        return participanteRepository.findByEventoId(eventId);
    }
    
}
