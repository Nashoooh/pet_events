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
import static org.mockito.Mockito.*;

public class ParticipanteServiceImplTest {

    // Simula el repositorio de participantes
    @Mock
    private ParticipanteRepository participanteRepository;

    // Simula el repositorio de eventos
    @Mock
    private EventoRepository eventoRepository;

    // Inyecta los mocks en el servicio que se está probando
    @InjectMocks
    private ParticipanteServiceImpl participanteService;

    private Evento evento;
    private Participante participante1;
    private Participante participante2;

    @BeforeEach
    void setUp() {
        // Inicializa los mocks y configura los datos de prueba
        MockitoAnnotations.openMocks(this);
        evento = new Evento(1L, "Adopción de Mascotas", "Evento para promover la adopción responsable de mascotas", LocalDate.of(2023, 12, 15), "Parque Central");
        participante1 = new Participante(1L, "Juan Pérez", "Firulais", "Perro", 3, evento);
        participante2 = new Participante(2L, "Ana Gómez", "Michi", "Gato", 2, evento);
    }

    @Test
    void testGetAllParticipantes() {
        // Simula que el repositorio devuelve una lista de participantes
        List<Participante> mockParticipantes = Arrays.asList(participante1, participante2);
        when(participanteRepository.findAll()).thenReturn(mockParticipantes);

        // Llama al método del servicio para obtener todos los participantes
        List<Participante> result = participanteService.getAllParticipantes();

        // Verifica que el resultado no sea nulo
        assertNotNull(result);
        // Verifica que la lista contiene exactamente 2 participantes
        assertEquals(2, result.size());
        // Verifica que el nombre del primer participante sea "Juan Pérez"
        assertEquals("Juan Pérez", result.get(0).getName());
        // Verifica que el método findAll() del repositorio fue llamado exactamente una vez
        verify(participanteRepository, times(1)).findAll();
    }

    @Test
    void testGetParticipanteById() {
        // Simula que el repositorio devuelve un participante específico al buscar por ID
        when(participanteRepository.findById(1L)).thenReturn(Optional.of(participante1));

        // Llama al método del servicio para obtener un participante por ID
        Participante result = participanteService.getParticipanteById(1L);

        // Verifica que el resultado no sea nulo
        assertNotNull(result);
        // Verifica que el nombre del participante sea "Juan Pérez"
        assertEquals("Juan Pérez", result.getName());
        // Verifica que el método findById(1L) del repositorio fue llamado exactamente una vez
        verify(participanteRepository, times(1)).findById(1L);
    }
}