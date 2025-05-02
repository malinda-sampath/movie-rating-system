# Movie Rating System

## Overview

This is a Movie Rating System built using Java 21, Spring Boot, and MySQL. The system allows users to:

- Add, remove, update, and view movie details.
- Add ratings (1-5) and optional reviews for movies.
- View all movies of a specific genre.
- View reviews for a movie based on its title and a specified rating.
- View the top-rated movie list based on average ratings.

## Features

- **Movies**: Add, update, delete, and view movie details.
- **Ratings**: Add ratings and reviews for movies. Users can filter reviews by title and rating.
- **Genres**: Movies can be categorized into genres such as Horror, Action, Romance, Comedy, and Science Fiction.
- **Top-Rated Movies**: Displays movies with at least five ratings, ranked by average rating.

## Technologies Used

- **Java**: 21
- **Spring Boot**: 3.4.x
- **MySQL**: 8.x
- **JPA/Hibernate**: For database interaction

## Prerequisites

- Java 21
- MySQL Database
- Maven

## Setup

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/movie-rating-system.git
cd movie-rating-system
```

### 2. Database Configuration

- Install MySQL locally or use a remote instance.
- Create a database (e.g., `mv_rating_db`).
- Update the `application.yaml` and `application-dev.yaml` files in the `src/main/resources` folder with your database connection details.

### **`application.yaml`** (for general configuration):

```yaml
spring:
  application:
    name: movie-rating-system

  profiles:
    active: dev

  datasource:
    url: ${SPRING_DATASOURCE_URL}
    username: ${SPRING_DATASOURCE_USERNAME}
    password: ${SPRING_DATASOURCE_PASSWORD}
    driver-class-name: com.mysql.cj.jdbc.Driver

server:
  port: 8082
```

### **`application-dev.yaml`** (for development environment-specific configuration):

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mv_rating_db?createDatabaseIfNotExist=true
    username: root
    password: root

  jpa:
    hibernate:
      ddl-auto: update
    database-platform: org.hibernate.dialect.MySQLDialect
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQLDialect

  sql:
    init:
      mode: always
```

- Set your MySQL database credentials as environment variables or in the `application.yaml` file (e.g., `${SPRING_DATASOURCE_URL}`).

### 3. Build the Project

```bash
mvn clean install
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

The application will run on `http://localhost:8082`.

### 5. Database Schema

The system uses the following entities:

- **Movie**: Stores movie details (title, genre, release date).
- **Rating**: Stores movie ratings and reviews, linked to the `Movie` entity.

### 6. API Endpoints

- **Add Movie**:

  - `POST /api/movie/add`
  - Request body: `MovieSaveDTO`

- **Get All Movies**:

  - `GET /api/movie/getAll`

- **Get Movie by ID**:

  - `GET /api/movie/get-by-id/{id}`

- **Update Movie**:

  - `PUT /api/movie/update/{id}`

- **Delete Movie**:

  - `DELETE /api/movie/delete-by-id/{id}`

- **Get Movies by Genre**:

  - `GET /api/movie/genre/{genre}`

- **Get Top-Rated Movies**:

  - `GET /api/movie/top-rated`

- **Add Rating**:

  - `POST /api/rating/add`
  - Request body: `RatingSaveDTO`

- **Get Reviews by Title and Rating**:

  - `GET /api/rating/reviews?title={title}&rating={rating}`

### 7. Running Tests

You can run tests using Maven:

```bash
mvn test
```

---
