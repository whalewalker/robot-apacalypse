# Robot Apacalypse
The year is 2050 and the world as we know it has been taken over by robots.
Created as once friendly robots, have now turned against humankind, especially
software engineers like yourself. Their mission is to transform everyone into
mindless zombies for their entertainment. You as a resistance member (and the last
survivor who knows how to code), was designated to develop a system to meet the
following requirements <br>
***The API documentation is hosted [here](https://documenter.getpostman.com/view/18385063/2s93Jus2bm)***


## Setting up the Spring Boot Application with Gradle

This is a guide to set up the Spring Boot application with Gradle.

## Prerequisites

- Java 11 or higher
- Gradle 5.6 or higher
- MySQL 5.6 or higher

## Installation

1. Clone this repository to your local machine:

    ```bash
    git clone https://github.com/your-username/thullo/thullo-backend.git
    ```

2. Create MySQL database

   ```bash
   mysql> create database robot_apocalypse
   ```

3. Configure database username and password

     ```properties
       # src/main/resources/application.properties
      spring.datasource.url=jdbc:mysql://localhost:3306/robot_apocalypse
      spring.datasource.username=<YOUR_DB_USERNAME>
      spring.datasource.password=<YOUR_DB_PASSWORD>
     ```
   
4.  Navigate to the root directory of the project.
    ```bash
    cd project_directory/
    ```

5. Build the project using Gradle.
   ```bash
   ./gradlew clean build
    ```
6. Run the project 
   ```bash
   ./gradlew bootRun
    ```
7. The Spring Boot application should start running and you should be able to access it at http://localhost:8080.

## Functional requirement
- Add survivors to the database
- Update survivor location
- Flag survivor as infected
- Connect to the Robot CPU system
- Sort robots into categories (flying and land)
- Provide percentage of infected survivors
- Provide percentage of non-infected survivors
- Provide a list of infected survivors
- Provide a list of non-infected survivors
- Provide a list of robots.

## Non-Functional Requirements
The following non-functional requirements must be met:
- **Availability**: The application must be highly available, with minimal downtime and interruptions in service.
- **Performance**: The application must be optimized for low latency, with fast response times to user requests.

