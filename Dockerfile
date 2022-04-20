FROM openjdk:8
EXPOSE 8082
ADD /target/EmployeeForum-0.0.1-SNAPSHOT.jar employeeforum.jar
ENTRYPOINT ["java","-jar","employeeforum.jar"]
