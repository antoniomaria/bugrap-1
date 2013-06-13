# Bugrap 

This is a small project for learning core Vaadin concepts. 

The project is split into three modules:
* bugrap-domain - contains entities and persistence logic
* bugrap-service - contains business logic, depends on bugrap-domain
* bugrap-ui - contains presentation code and UI logic, depends on bugrap-service and bugrap-domain

## Running

The easiest way to get the project running is to run the following maven target in bugrap-ui:

`mvn package tomee:run`

This will build the project and deploy it to a TomEE server using an in-memory HSQLDB. The deployed application is available at `http://localhost:8080/bugrap-ui-1.0-SNAPSHOT` (substitute current version for version number).