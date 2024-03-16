FROM openjdk:17
VOLUME /tmp
EXPOSE 8061
ARG JAR_FILE=account-boot/target/account-boot-0.1.0-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]