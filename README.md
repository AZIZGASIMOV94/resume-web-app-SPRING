# Resume Web App (Spring)

![Java 8](https://img.shields.io/badge/Java-8-orange)
![Spring Boot 2.4](https://img.shields.io/badge/Spring%20Boot-2.4-brightgreen)

A multi-module Spring Boot project for managing CV / resume data: users, their skills, employment history and nationality. One shared data module is used by two front ends: a secured REST API and a server-rendered JSP web app.

It was built in 2021 as the third version of the same app, after a plain JDBC version and a JPA version, to learn Spring Boot, Spring Data JPA and Spring Security.

## Modules

```
resume-web-app-SPRING
├── resumeAppSpring      # shared data layer: JPA entities, DAOs, services
├── resumerestapi        # REST API secured with OAuth2 + JWT
└── resumewebappspring   # JSP web app with form login
```

| Module | What it does | Key tech |
|---|---|---|
| `resumeAppSpring` | Entities (`UserTable`, `Skill`, `UserSkill`, `EmploymentHistory`, `CountryTable`), DAO layer and `UserService` (search, get, add, update, delete). Passwords hashed with BCrypt | Spring Data JPA, Hibernate, MySQL |
| `resumerestapi` | REST endpoints for users. Issues JWT access tokens (OAuth2 password grant); `GET /users` is admin-only. Includes a logging aspect and CORS filter | Spring Web, Spring Security OAuth2, JWT, Spring AOP, Actuator |
| `resumewebappspring` | Login page, searchable user list and user detail pages. `/users` requires the `ADMIN` role | Spring MVC, JSP/JSTL, Spring Security form login |

## REST API

| Method | Path | Description |
|---|---|---|
| `GET` | `/users?name=&surname=&nationality=` | Search users (all filters optional, `ADMIN` only) |
| `GET` | `/users/{id}` | Get one user |
| `POST` | `/users` | Create a user |
| `DELETE` | `/users/{id}` | Delete a user |

All endpoints except `/actuator/**` need a bearer token from `/oauth/token`.

## Running locally

**Requirements:** Java 8+, Maven, MySQL on `localhost:3306` with a database named `resume_db` (user `root`, empty password by default; change it in each module's `application.properties`).

```bash
# build everything (the shared module is built first)
mvn install -DskipTests

# run the REST API
cd resumerestapi && mvn spring-boot:run

# or run the JSP web app
cd resumewebappspring && mvn spring-boot:run
```

> The JWT signing key and OAuth2 client credentials in `resumerestapi/src/main/resources/application.properties` are demo values. Replace them before deploying anywhere.

## What I learned

- Splitting an app into a reusable data module and separate presentation modules
- Spring Data JPA entity relationships and custom queries
- Two styles of Spring Security: stateless OAuth2/JWT for an API, session-based form login for a web app
- Cross-cutting concerns with Spring AOP

