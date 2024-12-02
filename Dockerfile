FROM openjdk:18
WORKDIR /app
COPY ./target/customer-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "customer-0.0.1-SNAPSHOT.jar"]

FROM postgres:17.2
ENV POSTGRES_PASSWORD=postgres
ENV POSTGRES_DB=customerdb
COPY ./createdb.sql /docker-entrypoint-initdb.d/
