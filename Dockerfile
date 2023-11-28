FROM openjdk:22-slim
RUN  mkdir /app 
COPY . /app
WORKDIR /app
CMD javac main/*.java Player/*.java
