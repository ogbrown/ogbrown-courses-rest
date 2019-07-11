#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#VOLUME /var/lib/docker
#RUN pwd
#COPY . ./app
#WORKDIR /app
#RUN ls -l
#RUN cat Dockerfile
#RUN echo ".git" > .dockerignore
##RUN sh mvn -N io.takari:maven:wrapper
#RUN sh mvnw clean install
#RUN ls -l ./target
#ADD target/spring-boot-codebuild-0.1.0.jar /app.jar
#RUN pwd
#EXPOSE 8080
#ENV JAVA_OPTS=""
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]

FROM java:8
VOLUME /tmp
ARG JAR_FILE="target/ogbrown-courses-rest-0.1.0.jar"
#RUN ls -l ${JAR_FILE}
COPY ${JAR_FILE} app.jar
RUN ls -l app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

#ARG JAR_FILE
#COPY ${JAR_FILE} target/fargate.jar
#RUN sh -c 'touch target/fargate.jar'
#ENTRYPOINT ["java", "-jar","target/fargate.jar"]
#MAINTAINER ogbrown3@gmail.com