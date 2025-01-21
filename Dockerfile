# Utiliser une image de base pour Java
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le jar de l'application
COPY target/school_bdd-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port sur lequel votre application écoute
EXPOSE 8080

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
