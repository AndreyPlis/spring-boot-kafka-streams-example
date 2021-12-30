ARG DOCKER_REGISTRY=registry.access.redhat.com
FROM ${DOCKER_REGISTRY}/openjdk/openjdk-11-rhel7

ARG JAR=mock-service-1.0.0-SNAPSHOT.jar
COPY mock-service/build/libs/${JAR} /mock-service.jar

EXPOSE 8080
ENTRYPOINT ["java", "-Dfile-encoding=UTF-8", "-jar", "/mock-service.jar"]

