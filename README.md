# Employee Management System

A full-stack application to manage employees. This application showcases an Employee Table which allows CRUD operations on employee records. The backend is built using Spring Boot and MySQL, while the frontend is crafted with Vue.js, enhanced with Bootstrap for styling and SweetAlert for notifications.

## Features

- List all employees from the initial dataset (from `data.json`).
- View details such as first name, last name, and salary (formatted as currency).
- Edit employee details.
- Delete an employee record.
- Create a new employee.

## Prerequisites

- [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- [MySQL](https://dev.mysql.com/downloads/)
- [Node.js](https://nodejs.org/)
- [Maven](https://maven.apache.org/download.cgi)

## Setup and Installation

### Backend:

1. Navigate to the `backend` directory.
2. Update `src/main/resources/application.properties` to match your MySQL configuration.
3. Initialize the database with the provided SQL script.
   
    ```bash
    mysql -u YOUR_USERNAME -p < path_to_sql_script.sql
    ```

4. Build and package the project using Maven:

    ```bash
    ./mvnw clean package
    ```

5. Run the generated JAR:

    ```bash
    java -jar target/name_of_your_jar.jar
    ```

### Frontend:

1. Navigate to the `frontend` directory.
2. Install dependencies:

    ```bash
    npm install
    ```

3. Run the Vue.js application:

    ```bash
    npm run serve
    ```

The application will be accessible at `http://localhost:8080` (or another port if `8080` is occupied).

## Technologies Used

- **Backend:** Spring Boot for REST API and MySQL for database storage.
- **Frontend:** Vue.js for the frontend framework, Bootstrap for styling, and SweetAlert for user notifications and alerts.

## API Endpoints

| Endpoint                  | HTTP Method | Description                               | Request Body                                                        | Response Body                   |
|--------------------------|-------------|----------------------------------------|-------------------------------------------------------------------|---------------------------------|
| `/api/employee`           | `POST`      | Create a new employee                   | JSON of the employee (e.g., `{"employeeFirstName": "John"}`)      | JSON of the created employee    |
| `/api/employees`          | `GET`       | Retrieve all employees                  | None                                                              | JSON array of all employees     |
| `/api/employee/{id}`      | `GET`       | Retrieve a specific employee by its ID  | None                                                              | JSON of the retrieved employee  |
| `/api/employee/{id}`      | `PUT`       | Update a specific employee's details    | JSON with details to update (e.g., `{"employeeFirstName": "Jane"}`)| JSON of the updated employee    |
| `/api/employee/{id}`      | `DELETE`    | Delete a specific employee by its ID    | None                                                              | Message indicating success/failure  |

### Sample Request and Response

#### POST `/api/employee`
**Request Body:**
```json
{
    "employeeFirstName": "John",
    "employeeLastName": "Doe",
    "employeeSalary": 50000
}
