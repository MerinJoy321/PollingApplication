## Deploying to Railway (Spring Boot + MySQL)

### Prereqs
- Repo contains `mvnw` and `.mvn/` (already present).
- Java 17 (Railway build image has it).

### Railway setup
1) Create project → “Deploy from GitHub” → select this repo.
2) Add MySQL plugin (or point to your own MySQL). Railway will expose `MYSQLHOST`, `MYSQLPORT`, `MYSQLUSER`, `MYSQLPASSWORD`, `MYSQLDATABASE`.
3) Set service variables:
   - `SERVER_PORT=${PORT}`
   - `SPRING_DATASOURCE_URL=jdbc:mysql://${MYSQLHOST}:${MYSQLPORT}/${MYSQLDATABASE}?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false`
   - `SPRING_DATASOURCE_USERNAME=${MYSQLUSER}`
   - `SPRING_DATASOURCE_PASSWORD=${MYSQLPASSWORD}`
   - `SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver`
   - `APP_ADMIN_USERNAME=admin` (change if desired)
   - `APP_ADMIN_PASSWORD=admin123` (change if desired)
   - Optional: `SPRING_JPA_HIBERNATE_DDL_AUTO=update`
4) Build command: `./mvnw -DskipTests package`
5) Start command: `java -jar target/PollingApplication-0.0.1-SNAPSHOT.jar`

### Verification
- Check logs for:
  - `HikariPool-1 - Start completed`
  - `Tomcat started on port(s): <PORT>`
- Open the Railway-assigned URL (it maps to `${PORT}`) and load `/`.

### Notes
- If you prefer Docker: create a Dockerfile and run `docker build`/`docker run` on Railway, but the buildpack path above is simpler.
- If you see “Failed to determine a suitable driver class,” ensure the MySQL plugin is attached and the datasource env vars are set.

