FROM openjdk:11
MAINTAINER laundrohem.com

COPY build/libs/bookingsystem-0.0.1-SNAPSHOT.jar bookingsystem.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/bookingsystem.jar"]

