## LostLink Server – Run Instructions

This repository contains the backend API for **LostLink**, built with **Java / Spring Boot** and **Gradle**.  
Below are straightforward steps your instructor can use to run the project.

---

## 1. Prerequisites

- **Java**: JDK 17 or later
- **Gradle**: Not required globally – the project uses the included `gradlew` wrapper
- **External PostgreSQL database**: A reachable Postgres instance (cloud DB, university DB, department DB, etc.).  
  The application does **not** use a local or Dockerized database in this setup.

---

## 2. Environment configuration (`.env`)

1. In the project root, copy the example file:

   ```bash
   cp .env.example .env
   ```

2. Open `.env` and adjust the values as needed.  
   At minimum you will typically configure:

   - `SPRING_DATASOURCE_URL`
   - `SPRING_DATASOURCE_USERNAME`
   - `SPRING_DATASOURCE_PASSWORD`
   - any JWT / security or other custom variables used by the app

3. Example configuration for an **external PostgreSQL database**:

   ```env
   # External PostgreSQL (example)
   SPRING_DATASOURCE_URL=jdbc:postgresql://161.97.82.131:5492/postgres
   SPRING_DATASOURCE_USERNAME=postgres
   SPRING_DATASOURCE_PASSWORD=3ixsneikblxp9awg
   ```

   You will replace `db.example.edu`, database name, username and password with the **real** connection details of your department / cloud database.

4. The Spring Boot app reads these via `application.properties`, e.g.:

   - `server.port=${SERVER_PORT}`
   - `spring.datasource.url=${SPRING_DATASOURCE_URL}`

5. **Initial user requirement**  
   If you use a **different database** than the one already prepared for this project, the initial user must be created directly in the database (e.g. via SQL, pgAdmin, or your DB admin tool).  
   The application expects at least one user row in the `users` table (for example an admin account) so that login and protected endpoints can be tested.

---

## 3. Option A – Run locally (app on host, external database)

This runs the Spring Boot application directly on your machine and connects to the configured external database.

1. **Clone** the repository and move into it:

   ```bash
   git clone <REPO_URL>
   cd server
   ```

2. **Ensure the external PostgreSQL database is reachable** from your machine:

   - Use the same host, database, user and password that you configured in `.env`.
   - Test with a client (e.g. `psql`, DBeaver, pgAdmin) if needed.

3. **Run the application** using the Gradle wrapper:

   ```bash
   ./gradlew bootRun
   ```

   On Windows (PowerShell or CMD):

   ```bash
   .\gradlew.bat bootRun
   ```

4. By default (with a typical `.env`), the API will be available at:

   - **Base URL**: `http://localhost:<SERVER_PORT>/api/v1`
   - **Swagger UI**: `http://localhost:<SERVER_PORT>/api/v1/swagger`

---

## 4. Option B – Run with Docker (app in Docker, external database)

This option builds a Docker image for the Spring Boot app and runs **only the app** in Docker.  
It still connects to the same external PostgreSQL database configured in `.env`.

1. Make sure `.env` exists and is configured as described in section 2 (pointing to the external DB, not to `localhost` in another container).

2. **Build the application image** from the project root:

   ```bash
   docker build -t lostlink-server .
   ```

3. **Run the application container**, passing in the `.env` file:

   ```bash
   docker run --env-file .env -p 8081:8081 --name lostlink-server lostlink-server
   ```

   - Adjust `8081:8081` if your `SERVER_PORT` in `.env` is different.
   - Ensure your `SPRING_DATASOURCE_URL` host is reachable from inside the container  
     (for example a public DB hostname like `db.example.edu`, **not** `localhost`).

4. The API will then be accessible at:

   - `http://localhost:8081/api/v1` (or the port you mapped)
   - `http://localhost:8081/api/v1/swagger` for Swagger UI

---

## 5. Stopping the Dockerized app

- To stop and remove the application container:

  ```bash
  docker rm -f lostlink-server
  ```

This should be all your instructor needs to clone the repo, configure `.env` for an **external PostgreSQL database**, ensure the initial user exists in that database, and run the backend either directly or with Docker for the application only.
