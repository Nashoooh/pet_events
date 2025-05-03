package com.example.pet_events.service;

import com.example.pet_events.model.Evento;
import com.example.pet_events.model.Participante;
import com.example.pet_events.repository.EventoRepository;
import com.example.pet_events.repository.ParticipanteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ParticipanteServiceImplTest {

    @Mock
    private ParticipanteRepository participanteRepository;

    @Mock
    private EventoRepository eventoRepository;

    @InjectMocks
    private ParticipanteServiceImpl participanteService;

    private Evento evento;
    private Participante participante1;
    private Participante participante2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        evento = new Evento(1L, "Adopción de Mascotas", "Evento para promover la adopción responsable de mascotas", LocalDate.of(2023, 12, 15), "Parque Central");
        participante1 = new Participante(1L, "Juan Pérez", "Firulais", "Perro", 3, evento);
        participante2 = new Participante(2L, "Ana Gómez", "Michi", "Gato", 2, evento);
    }

    @Test
    void testGetAllParticipantes() {
        List<Participante> mockParticipantes = Arrays.asList(participante1, participante2);

        when(participanteRepository.findAll()).thenReturn(mockParticipantes);

        List<Participante> result = participanteService.getAllParticipantes();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Juan Pérez", result.get(0).getName());
        verify(participanteRepository, times(1)).findAll();
    }

    @Test
    void testGetParticipanteById() {
        when(participanteRepository.findById(1L)).thenReturn(Optional.of(participante1));

        Participante result = participanteService.getParticipanteById(1L);

        assertNotNull(result);
        assertEquals("Juan Pérez", result.getName());
        verify(participanteRepository, times(1)).findById(1L);
    }

}