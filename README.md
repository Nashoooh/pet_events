# Eventos de Mascotas
Proyecto Spring Boot con Eventos de Mascotas - Exp3_S8_Ignacio_Andana

## Descripción
Este proyecto es un API Rest de Eventos de Mascotas, diseñado para aprender los conceptos básicos de Spring Boot. Permite realizar operaciones CRUD sobre una lista de eventos y participantes almacenadas en BD Oracle, realizar pruebas unitarias y levantar proyecto localmente y en Docker.

## Funcionalidades
- GET /eventos
- POST /eventos
- GET /eventos/{id}
- POST /eventos/{eventoId}/participantes
- GET /eventos/{eventoId}/participantes
- DELETE /eventos/{eventoId}/participantes/{participanteId}
- PUT /eventos/{eventoId}/participantes/{participanteId}


## Tecnologías
- Java 21
- Spring Boot
- Postman

## Instrucciones para levantar el servicio

### Prerrequisitos
1. **Java 21**: Instalar Java 21 y agregar al PATH.
2. **Maven 3.9.9**: Instalar Maven 3.9.9 y agregado al PATH.
3. **Visual Studio Code**: Instalar el IDLE vscode.
4. **Extensiones de Visual Studio Code**:
   - Extensión Pack for Java
   - Spring Boot Extension Pack
   - Material Icon Theme

### Pasos para levantar el servicio

1. **Clonar el repositorio**:
   Abrir una terminal 
   git clone https://github.com/Nashoooh/pet_events.git
   cd pet_events

2. **Compilar proyecto**:
    mvn clean install

3. **Levantar servicio**:
    - Abrir la paleta de comandos con Ctrl + Shift + P -> Seleccionar "Spring Boot Dashboard"
    - Seleccionar el proyecto llamado "pet_events"
    - Seleccionar algun archivo dentro del proyecto con extension .java para que aparezca el boton de play para ejecutar la aplicación en la esquina superior derecha.
    - Presionar el boton play o F5.

4. **Verificar que el servicio este ejecutandose**:
    Navega a http://localhost:9000/events y deberia cargar la lista de eventos creados en el ArrayList en JSON.
