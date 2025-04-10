# Dinner Club Management Application

This is a Spring Boot application designed to manage dinner club events. It provides functionalities to create and manage events, guests, invitations, and themes. Additionally, it offers analytics features to track attendance patterns, such as identifying frequent no-shows, reliable guests, and theme preferences.

## Technologies Used

- **Java 21 (Amazon Corretto 21.0.6)**: The programming language and runtime environment.
- **Spring Boot 3.4.4**: The framework for building the application, providing embedded server and dependency management.
- **Maven**: The build tool for managing dependencies and project lifecycle.
- **Spring Data JPA**: For object-relational mapping (ORM) and database interactions.
- **PostgreSQL 17**: The relational database management system, run via Docker.
- **Docker**: For containerizing the PostgreSQL database.

### Development Tools

- **IntelliJ IDEA**: The integrated development environment (IDE) used for development.
- **DBeaver**: A database management tool for interacting with PostgreSQL.
- **Swagger (springdoc-openapi-starter-webmvc-ui 2.8.6)**: For generating and accessing API documentation.

### Key Dependencies

The project uses the following Maven dependencies:
- `spring-boot-starter-data-jpa`: For JPA and database access.
- `spring-boot-starter-validation`: For bean validation.
- `spring-boot-starter-web`: For building RESTful web services.
- `spring-boot-devtools`: For development-time enhancements (optional).
- `springdoc-openapi-starter-webmvc-ui:2.8.6`: For Swagger API documentation.
- `postgresql`: The PostgreSQL JDBC driver (runtime scope).
- `lombok`: For reducing boilerplate code.
- `spring-boot-starter-test`: For testing (test scope).
- `org.jetbrains:annotations`: For code annotations.

## Setup Instructions

Follow these steps to set up and run the application locally:

1. **Install Java and Maven:**
   - Ensure Java 21 (Amazon Corretto) and Maven are installed. Download them from:
     - [Amazon Corretto 21](https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html)
     - [Apache Maven](https://maven.apache.org/download.cgi)

2. **Install Docker:**
   - Install Docker to run the PostgreSQL database. Get it from [Docker's official website](https://www.docker.com/get-started).

3. **Run PostgreSQL with Docker:**
   - Pull and start a PostgreSQL 17 container:
     ```bash
     docker pull postgres:17
     docker run --name dinnerclub-db -e POSTGRES_PASSWORD=mysecretpassword -d -p 5432:5432 postgres:17
   - This creates a container named dinnerclub-db with the password mysecretpassword. Adjust the password as needed.

4. **Clone the Repository:**
   - Clone the project from GitHub:
     ```git
     git clone https://github.com/AndrijaSniffer/java-dinner-club.git

5. **Configure Database Connection:**
   - Open `src/main/resources/application.properties` and add the following:
       ```java
       spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
       spring.datasource.username=postgres
       spring.datasource.password=mysecretpassword
  - Note: The default database is postgres. If your application uses a different database name, create it in PostgreSQL (e.g., using DBeaver or psql) and update the spring.datasource.url accordingly.

6. **Run the Application:**
  - Navigate to the project directory:
    ```cmd
    cd dinnerClub

  - Start the application with Maven:
    ```cmd
    mvn spring-boot:run
  *Or start the application through an IDE such as IntelliJ.*
  
  - The application will run on [http://localhost:8080](http://localhost:8080) by default.
  - API Docummentation (Swagger) can be found at [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)
