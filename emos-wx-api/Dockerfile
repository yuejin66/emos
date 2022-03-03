FROM openjdk:11
MAINTAINER 13727764936@163.com
COPY src/main/resources/application-prod.yml application.yml
COPY target/emos.jar emos.jar
VOLUME /var/emos
CMD ["java","-Djava.security.edg=file:/dev/.urandom","-Dfile.encoding=UTF-8","-Duser.timezone=GMT+08","-jar","/emos.jar"]