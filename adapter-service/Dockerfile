ARG DOCKER_REGISTRY=registry.access.redhat.com
FROM ${DOCKER_REGISTRY}/openjdk/openjdk-11-rhel7

ARG JAR=adapter-service-1.0.0-SNAPSHOT.jar
COPY adapter-service/build/libs/${JAR} /adapter-service.jar

EXPOSE 8080
ENTRYPOINT ["java", "-Dfile-encoding=UTF-8", "-jar", "/adapter-service.jar"]

