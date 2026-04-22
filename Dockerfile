# Utiliser l'image officielle Maven pour Java 21
FROM maven:3.9.6-eclipse-temurin-21

# Créer un répertoire de travail
WORKDIR /app

# Copier le code local dans le conteneur
COPY projetDevops/pom.xml .
COPY projetDevops/src ./src

# Compiler et lancer la démonstratio
CMD ["mvn", "test"]
