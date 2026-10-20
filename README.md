# Car Sharing Backend

A Java and Spring Boot project developed as part of my studies at FH Technikum Wien.

The application implements backend functionality for a car-sharing service, including user management, fleet management, REST APIs, and RabbitMQ messaging.

## Technologies

- Java
- Spring Boot
- Spring Web
- REST API
- RabbitMQ
- Maven
- Docker Compose

## Implemented Features

### User Management
- User registration and login
- Token-based session management
- Role-based access control

### Vehicle Management
- REST endpoints for creating, retrieving, updating, and deleting vehicles
- Fleet manager access control

### Messaging
- RabbitMQ configuration
- Publishing vehicle status updates
- Publishing emergency notifications

## API Endpoints

### Users
- `POST /api/users/register`
- `POST /api/users/login`
- `POST /api/users/logout`
- `GET /api/users`

### Vehicles
- `POST /api/vehicles`
- `GET /api/vehicles`
- `GET /api/vehicles/{id}`
- `PUT /api/vehicles/{id}`
- `DELETE /api/vehicles/{id}`

### Vehicle Devices
- `POST /api/devices/{vehicleId}/status`
- `POST /api/devices/{vehicleId}/alarm`

## Running the Application

Requirements:
- Java Development Kit
- Maven
- Docker, if running RabbitMQ locally

Start RabbitMQ using Docker Compose:

```bash
docker compose up -d
```

Run the Spring Boot application:

```bash
./mvnw spring-boot:run
```

On Windows, use:

```bat
mvnw.cmd spring-boot:run
```

## Project Status

This is a university project demonstrating selected backend development concepts. The current version uses in-memory storage for users and vehicles and includes RabbitMQ message publishing.

JPA-based persistence, the billing consumer, and invoice generation are not implemented in the current version.

## Academic Context

Developed as part of practical coursework at FH Technikum Wien.
