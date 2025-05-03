# Dockerfile

# Usa una imagen base con OpenJDK
FROM openjdk:21-ea-24-oracle

# Directorio de trabajo dentro del contenedor
WORKDIR /pet_events

# Copiar la wallet desde la raíz del proyecto
COPY wallet wallet/
EXPOSE 9000

# Copiar el archivo JAR generado por Maven
# Asegúrate de que el nombre del JAR coincida (ej: pet-events-0.0.1-SNAPSHOT.jar)
COPY target/pet_events-0.0.1-SNAPSHOT.jar app.jar

# Ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]