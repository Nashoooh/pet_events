package com.example.pet_events.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pet_events.repository.EventoRepository;
import com.example.pet_events.model.Evento;

@Service
public class EventoServiceImpl implements EventoService {
    
    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    @Override
    public Evento getEventoById(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    @Override
    public Evento createEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Override
    public Evento updateEvento(Long id, Evento evento) {
        if (eventoRepository.existsById(id)) {
            evento.setId(id);
            return eventoRepository.save(evento);
        }
        return null;
    }

    @Override
    public void deleteEvento(Long id) {
        if (eventoRepository.existsById(id)) {
            eventoRepository.deleteById(id);
        }
    }
}
