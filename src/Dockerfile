FROM openjdk:22-slim

RUN mkdir /app
COPY . /app
WORKDIR /app

# Compilează clasele Java
RUN javac main/*.java Player/*.java

# Rulează aplicația Java
CMD ["java", "main/Main"]

