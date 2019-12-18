FROM openjdk:11.0.5-jre-stretch

COPY build/libs/*.jar /szzti.jar

EXPOSE 8080
CMD java $JAVA_OPTS -jar szzti.jar