package com.example.pet_events.repository;

import com.example.pet_events.model.Participante;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    List<Participante> findByEventoId(Long eventoId);
}
