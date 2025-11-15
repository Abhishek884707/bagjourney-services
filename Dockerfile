FROM openjdk:26-ea-jdk
RUN pwd
RUN ls -ltr
COPY target/BagJourneyServices_1.jar .
RUN ls -ltr
EXPOSE 8080
ENTRYPOINT ["java","-jar","/BagJourneyServices_1.jar"]
