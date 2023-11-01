FROM openjdk:20
RUN  mkdir /app 
COPY . /app
WORKDIR /app
CMD java -jar Game.jar
