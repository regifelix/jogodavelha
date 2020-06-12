FROM java:8-jdk-alpine
COPY ./target/jogodavelha-1.0.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch jogodavelha-1.0.jar'
ENTRYPOINT ["java","-jar","jogodavelha-1.0.jar"]
