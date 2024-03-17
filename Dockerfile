FROM openjdk:17-alpine
COPY target/Hentori-1.0.0.jar Hentori-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/Hentori-1.0.0.jar"]