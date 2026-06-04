# Fase 1: Compilazione (Build) con Maven e Java 21
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copia il file pom.xml per scaricare le dipendenze in anticipo (ottimizza la cache di Docker)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia il codice sorgente del progetto e compila il file .jar ignorando i test temporaneamente
COPY src ./src
RUN mvn clean package -DskipTests

# Fase 2: Immagine di esecuzione leggera
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia il file .jar appena generato dalla fase precedente
COPY --from=build /app/target/*.jar app.jar

# Espone la porta 8081 utilizzata dalla tua applicazione
EXPOSE 8081

# Comando di avvio del container
ENTRYPOINT ["java", "-jar", "app.jar"]