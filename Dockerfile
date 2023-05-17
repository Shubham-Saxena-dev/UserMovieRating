FROM openjdk:11-jre-slim-buster
WORKDIR /app
COPY . /app
COPY target/UserMovieRating-1.0-SNAPSHOT.jar UserMovieRating.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "UserMovieRating.jar"]