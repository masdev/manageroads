FROM openjdk:17
ADD target/manageroads-0.0.1-SNAPSHOT.jar manageroads.jar
ENTRYPOINT [ "java", "-jar","manageroads.jar" ]