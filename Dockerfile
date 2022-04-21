FROM adoptopenjdk/openjdk11
COPY /build/libs/TCA3-0.0.1-SNAPSHOT.jar .
CMD java -jar TCA3-0.0.1-SNAPSHOT.jar