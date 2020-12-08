FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/app
COPY myService.jar /opt/app
CMD ["java", "-jar", "/opt/app/myService.jar"]