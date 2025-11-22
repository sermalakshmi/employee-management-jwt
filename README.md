Employee Management (Spring Boot + PostgreSQL + JWT)
This is a ready-to-run Java Spring Boot backend with:

CRUD APIs for Employee
JWT authentication (register/login)
PostgreSQL datasource (configure in application.properties)
Simple user table to manage credentials
Getting started
Ensure Java 17+ (this project uses release 21 recommended) and Maven are installed.

Create PostgreSQL database:

CREATE DATABASE employee_db;

Update src/main/resources/application.properties with your Postgres username/password.

Build and run: --bash mvn -U clean install mvn spring-boot:run

Test endpoints:

Register: POST /auth/register Body: { "username":"admin","password":"admin","role":"ROLE_ADMIN" }

Login: POST /auth/login Body: { "username":"admin","password":"admin" } Response: { "token":"..." }

Use token: Header: Authorization: Bearer Then call Employee endpoints: GET /api/employees POST /api/employees
