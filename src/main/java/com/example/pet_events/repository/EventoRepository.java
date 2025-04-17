package com.example.pet_events.repository;

import com.example.pet_events.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    
}