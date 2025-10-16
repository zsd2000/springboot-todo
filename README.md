# Spring Boot To-Do Application

A backend REST API built with Spring Boot to demonstrate understanding of software architecture, testing, and modern backend development practices.

---

## Overview

This project provides CRUD operations for managing to-do tasks.
It follows a layered architecture (Controller–Service–Repository) and was designed to practice professional Spring Boot development principles.
The application includes unit testing, Postman validation, and detailed JavaDocs across all layers.

---

## Features

* Full CRUD functionality for task management
* Layered architecture with Dependency Injection
* RESTful API design with proper HTTP status codes
* Unit tests using JUnit 5 and Mockito
* Integration testing through Postman
* H2 in-memory database for persistence

---

## Technologies

* **Java 17**
* **Spring Boot 3**
* **Spring Data JPA**
* **H2 Database**
* **JUnit 5 / Mockito**
* **IntelliJ IDEA**, **Postman**, **Git**, **GitHub**

---

## Endpoints

| Method | Endpoint      | Description         |
| ------ | ------------- | ------------------- |
| POST   | `/tasks`      | Create a task       |
| GET    | `/tasks`      | Retrieve all tasks  |
| GET    | `/tasks/{id}` | Retrieve task by ID |
| PUT    | `/tasks/{id}` | Update a task       |
| DELETE | `/tasks/{id}` | Delete a task       |

---

## Running the Application

1. Clone the repository

   ```bash
   git clone https://github.com/YOUR_USERNAME/springboot-todo.git
   cd springboot-todo
   ```
2. Run the application

   ```bash
   ./mvnw spring-boot:run
   ```
3. Test endpoints with Postman at `http://localhost:8080`

---

## Author

**Zachary Davis**
Software Engineering Student, Arizona State University
