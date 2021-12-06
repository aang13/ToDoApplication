# ToDoApplication
Simple ToDo App using spring-boot, postgres

Run the application from command line:
mvn spring-boot run

The application will run on the localhost:8080 port
The endpoints:
List can be found from here:
http://localhost:8080/swagger-ui.html#/

Build the docker image
docker build --tag=todo:latest .
Run the Docker image
docker run -p 8080:8080 todo:latest
