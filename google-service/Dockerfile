FROM openjdk

EXPOSE 8085

ADD target/google-service-address.jar google-service-address.jar

ENTRYPOINT ["java", "-jar", "google-service-address.jar"]

ENV TZ Europe/Israel
