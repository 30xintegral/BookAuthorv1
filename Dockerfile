FROM  --platform=linux/amd64 openjdk:17-alpine

WORKDIR /usr/src/app
COPY build/libs/Tutorial1-0.0.1-SNAPSHOT.jar target/tutorial1.jar


ENTRYPOINT ["java", "-jar", "/usr/src/app/target/tutorial1.jar"]
