## Introduction and Technologies stack
This is a CRM application to manage Clients and their Invoces.
Every User registered can Login or can SignUp if they are new to the service,
then they can view the list of all the Clients
and their invoces and have different ways of looking for them within the application.
Loging in with the Username "admin" and password "admin" gives the User extra-privileges
such us deleting a client or an invoice, updating them or saving new ones in the system.

### Tech stack: 
- Java + Spring Boot
- Authentication through Jason Web Token for the API RESTfull service
- Authentication for the Web Service (including Login and Signup)
- Secure storage of the password using encryption
- Support of Pagination
- Auto-calculation of Clients total income based on their Invoces
- JUnit
- Swagger Documentation
- Eclipse
- Postgress DBMS
- Thymeleaf for the Web Service (Front-End)
- Advanced REST Client
- Maven
- Git and Gihub, of course : ) 

Main Features implemented

- Web service
- Input/Output via REST API

------------------------------------------------------------------------------------------

Sarà possibile utilizzare SwaggerOpenApi per testare i servizi ed accedere alla documentazione tramite l'endpoint : http://localhost:8080/swagger-ui.html

Qui sarà richiesta l'autenticazione per utilizzare la maggior parte dei servizi esposti come: http://localhost:8080/api/

mentre sarà sempre possibile accedere ai servizi di signup e login tramite endpoint: http://localhost:8080/auth/


