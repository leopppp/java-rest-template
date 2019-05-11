FROM maven:3.5.4-jdk-8-alpine
RUN mkdir -p /app/resttemplate/src
COPY pom.xml /app/resttemplate/.
COPY src /app/resttemplate/src/.
EXPOSE 8080
RUN cd /app/resttemplate && mvn clean install
ENTRYPOINT ["/bin/bash","-c","cd /app/resttemplate && \
                               java -jar target/resttemplate-1.0.jar"]