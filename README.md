# Student Management System

## Overview

The Student Management System is a RESTful API built with Spring Boot. It manages students and their enrollment in various courses. The system allows users to perform CRUD operations on students and courses, and it maintains the many-to-many relationship between students and courses.

## Features

- Add, update, delete, and retrieve students.
- Add, update, delete, and retrieve courses.
- Enroll students in courses and list the courses a student is enrolled in.
- List students enrolled in a particular course.

## Technical Overview

### Architecture

The application is built using the Spring Boot framework and follows the MVC (Model-View-Controller) design pattern. It uses JPA (Java Persistence API) for ORM (Object-Relational Mapping) to interact with a relational database.

### Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database (for development)
- Maven (for dependency management)

### Database Schema

The application uses two main entities: `Student` and `Course`. They have a many-to-many relationship, which is managed by a join table `student_course`.

#### Student

- `id` (Long): Primary key
- `name` (String): Name of the student
- `email` (String): Email of the student
- `courses` (List<Course>): List of courses the student is enrolled in

#### Course

- `id` (Long): Primary key
- `name` (String): Name of the course
- `description` (String): Description of the course
- `students` (List<Student>): List of students enrolled in the course

### JSON Serialization

To handle the many-to-many relationship without causing infinite recursion during JSON serialization, `@JsonIdentityInfo` is used.

### Dependabot and Linter

Dependabot is configured in this project to automatically check for updates to the project's dependencies and create pull requests for any updates it finds. This helps to keep the project secure and up-to-date with the latest library versions.

A linter is also added to the project to ensure code quality and consistency. The linter checks the code against a set of defined rules and reports any issues found, which helps maintain a clean and readable codebase.

## Endpoints

### Students

#### Add a Student

- **URL:** `/students`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
      "name": "John Doe",
      "email": "john.doe@example.com",
      "courses": [
          {
              "name": "Science 101",
              "description": "Introduction to Science"
          }
      ]
  }
  ```
- **Response Body:**
  ```json
  {
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "courses": [
        {
            "id": 1,
            "name": "Science 101",
            "description": "Introduction to Science",
            "students": [1]
        }
    ]
  }
  ```

#### Get All Students

- **URL:** `/students`
- **Method:** `GET`
- **Response Body:**
  ```json
  [
    {
        "id": 1,
        "name": "John Doe",
        "email": "john.doe@example.com",
        "courses": [
            {
                "id": 1,
                "name": "Science 101",
                "description": "Introduction to Science",
                "students": [1]
            }
        ]
    }
  ]
  ```

#### Get a Student by ID

- **URL:** `/students/{id}`
- **Method:** `GET`
- **Response Body:**
  ```json
  {
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "courses": [
        {
            "id": 1,
            "name": "Science 101",
            "description": "Introduction to Science",
            "students": [1]
        }
    ]
  }
  ```

#### Update a Student

- **URL:** `/students/{id}`
- **Method:** `PUT`
- **Request Body:**
  ```json
  {
    "name": "John Doe Updated",
    "email": "john.doe.updated@example.com"
  }
  ```
- **Response Body:**
  ```json
  {
    "id": 1,
    "name": "John Doe Updated",
    "email": "john.doe.updated@example.com",
    "courses": [
        {
            "id": 1,
            "name": "Science 101",
            "description": "Introduction to Science",
            "students": [1]
        }
    ]
  }
  ```

#### Delete a Student

- **URL:** `/students/{id}`
- **Method:** `DELETE`
- **Response Body:**
  ```text
  204 No Content
  ```

### Courses

#### Add a Course

- **URL:** `/courses`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "name": "Math 101",
    "description": "Introduction to Mathematics"
  }
  ```

- **Response Body:**
  ```json
  {
    "id": 2,
    "name": "Math 101",
    "description": "Introduction to Mathematics"
  }
  ```

#### Get All Courses

- **URL:** `/courses`
- **Method:** `GET`
- **Response Body:**
  ```json
  [
    {
        "id": 1,
        "name": "Science 101",
        "description": "Introduction to Science",
        "students": [
            {
                "id": 1,
                "name": "John Doe",
                "email": "john.doe@example.com",
                "courses": [1]
            }
        ]
    }
  ]
  ```

#### Get a Course by ID

- **URL:** `/courses/{id}`
- **Method:** `GET`
- **Response Body:**
  ```json
  {
    "id": 1,
    "name": "Science 101",
    "description": "Introduction to Science",
    "students": [
        {
            "id": 1,
            "name": "John Doe",
            "email": "john.doe@example.com",
            "courses": [1]
        }
    ]
  }
  ```

#### Update a Course

- **URL:** `/courses/{id}`
- **Method:** `PUT`
- **Request Body:**
  ```json
  {
    "name": "Advanced Science 101",
    "description": "Advanced Introduction to Science"
  }
  ```
- **Response Body:**
  ```json
  {
    "id": 1,
    "name": "Advanced Science 101",
    "description": "Advanced Introduction to Science",
    "students": [
        {
            "id": 1,
            "name": "John Doe",
            "email": "john.doe@example.com",
            "courses": [1]
        }
    ]
  }
  ```

#### Delete a Course

- **URL:** `/courses/{id}`
- **Method:** `DELETE`
- **Response Body:**
  ```text
  204 No Content
  ```

## Running the Application

### Clone the Repository:
  ```shell
  git clone https://github.com/your-username/student-management-system.git
  cd student-management-system
  ```

### Build the Project:
  ```shell
  mvn clean install
  ```

### Run the Application:
  ```shell
  mvn spring-boot:run
  ```
### Run the Application:
- The API will be available at http://localhost:8080

## Testing the Endpoints

You can use tools like Postman or curl to test the endpoints.

### Example CURL Requests

- The API will be available at http://localhost:8080
```shell
  curl -X POST http://localhost:8080/students \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john.doe@example.com",
    "courses": [
        {
            "name": "Science 101",
            "description": "Introduction to Science"
        }
    ]
}'
  ```

- The API will be available at http://localhost:8080
  ```shell
  curl http://localhost:8080/students
  ```
  
- The API will be available at http://localhost:8080
  ```shell
  curl http://localhost:8080/courses
  ```

## Conclusion

This Student Management System provides a comprehensive API for managing students and courses, demonstrating the use of Spring Boot, Spring Data JPA, and relational database management in a many-to-many relationship context.






