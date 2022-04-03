FROM maven as builder
COPY . /code/
WORKDIR /code/backend/webandtech
RUN mvn package

FROM openjdk:17-oracle
COPY --from=builder /code/target/*.jar /usr/src/
WORKDIR /usr/src
CMD [ "java", "-jar", "webandtech-0.0.1-SNAPSHOT.jar" ]