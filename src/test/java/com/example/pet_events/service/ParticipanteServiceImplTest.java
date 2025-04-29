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

    @Test
    void testGetParticipanteByIdNotFound() {
        when(participanteRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            participanteService.getParticipanteById(99L);
        });

        assertEquals("Participante no encontrado con ID: 99", exception.getMessage());
        verify(participanteRepository, times(1)).findById(99L);
    }

    @Test
    void testCreateParticipante() {
        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));
        when(participanteRepository.save(any(Participante.class))).thenAnswer(invocation -> invocation.getArgument(0)); // Devuelve el mismo objeto que se pasa
    
        Participante newParticipante = new Participante(null, "Carlos López", "Rex", "Perro", 4, evento);
        Participante result = participanteService.createParticipante(newParticipante);
    
        assertNotNull(result);
        assertEquals("Carlos López", result.getName()); // Ahora debería coincidir
        verify(eventoRepository, times(1)).findById(1L);
        verify(participanteRepository, times(1)).save(newParticipante);
    }

    @Test
    void testUpdateParticipante() {
        when(participanteRepository.findById(1L)).thenReturn(Optional.of(participante1));
        when(participanteRepository.save(any(Participante.class))).thenReturn(participante1);

        Participante updatedParticipante = new Participante(1L, "Juan Pérez Actualizado", "Firulais", "Perro", 3, evento);
        Participante result = participanteService.updateParticipante(1L, updatedParticipante);

        assertNotNull(result);
        assertEquals("Juan Pérez Actualizado", result.getName());
        verify(participanteRepository, times(1)).findById(1L);
        verify(participanteRepository, times(1)).save(updatedParticipante);
    }

    @Test
    void testDeleteParticipante() {
        when(participanteRepository.findById(1L)).thenReturn(Optional.of(participante1));

        participanteService.deleteParticipante(1L);

        verify(participanteRepository, times(1)).findById(1L);
        verify(participanteRepository, times(1)).delete(participante1);
    }

    @Test
    void testDeleteParticipanteNotFound() {
        when(participanteRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            participanteService.deleteParticipante(99L);
        });

        assertEquals("Participante no encontrado con ID: 99", exception.getMessage());
        verify(participanteRepository, times(1)).findById(99L);
        verify(participanteRepository, never()).delete(any(Participante.class));
    }

    @Test
    void testGetParticipantByEvent() {
        List<Participante> mockParticipantes = Arrays.asList(participante1, participante2);

        when(participanteRepository.findByEventoId(1L)).thenReturn(mockParticipantes);

        List<Participante> result = participanteService.getParticipantByEvent(1L);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Juan Pérez", result.get(0).getName());
        verify(participanteRepository, times(1)).findByEventoId(1L);
    }
}