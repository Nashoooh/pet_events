package com.example.pet_events.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEventNotFoundException(EventNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Evento no encontrado");
        errorResponse.put("message", ex.getMessage()); // Aquí se incluye el mensaje de la excepción
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ParticipantNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleParticipantNotFoundException(ParticipantNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Participante no encontrado");
        errorResponse.put("message", ex.getMessage()); // Aquí se incluye el mensaje de la excepción
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
