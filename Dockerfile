FROM anapsix/alpine-java:jre8

COPY build/libs/*.jar /szzti.jar

EXPOSE 8080
CMD java $JAVA_OPTS -jar szzti.jar