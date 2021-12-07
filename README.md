# ToDoApplication
Simple ToDo App using: <br/>
<ul>
<li>spring-boot, spring data-jpa</li>
<li>postgres </li>
<li>docker</li>
</ul>

###Run the application from command line
`mvn spring-boot run`

The application will run on:<br/>
`http://localhost:8080/` <br/>
:
###The endpoints list can be found from here:
Run the application and go to this url:<br/>
http://localhost:8080/swagger-ui.html#/

Snapshot of the list:
![img_1.png](img_1.png)

Here is the entity to use while making request:
![img.png](img.png)
##Running on docker
Create the jar:<br/><br/>
`mvn clean package`

###Build the docker image
`docker build --tag=todo:latest .`
###Run the Docker image
`docker run -p 8080:8080 todo:latest`
