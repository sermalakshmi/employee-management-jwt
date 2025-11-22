
# Employee Management (Spring Boot + PostgreSQL + JWT)

This is a ready-to-run Java Spring Boot backend with:
- CRUD APIs for Employee
- JWT authentication (register/login)
- PostgreSQL datasource (configure in application.properties)
- Simple user table to manage credentials

## Getting started

1. Ensure Java 17+ (this project uses release 21 recommended) and Maven are installed.
2. Create PostgreSQL database:
   ```sql
   CREATE DATABASE employee_db;
   ```
3. Update `src/main/resources/application.properties` with your Postgres username/password.
4. (Optional) Set JWT secret in environment:
   - Linux/Mac:
     ```bash
     export JWT_SECRET="your_very_long_secret_here"
     ```
   - Windows (PowerShell):
     ```powershell
     $env:JWT_SECRET="your_very_long_secret_here"
     ```

5. Build and run:
```bash
mvn -U clean install
mvn spring-boot:run
```

6. Test endpoints:
- Register:
  POST /auth/register
  Body: { "username":"admin","password":"admin","role":"ROLE_ADMIN" }

- Login:
  POST /auth/login
  Body: { "username":"admin","password":"admin" }
  Response: { "token":"..." }

- Use token:
  Header: Authorization: Bearer <token>
  Then call Employee endpoints:
  GET /api/employees
  POST /api/employees
  ...

## Resume reference
Local copy of resume (for your reference): /mnt/data/Sermalakshmi_Resume.pdf.pdf
