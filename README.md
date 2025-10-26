# 📋 Tasks Microservice

A Spring Boot microservice for task management with automated notification scheduling, part of a distributed microservices architecture.

## 🚀 Overview

The Tasks Microservice is responsible for managing user tasks with due dates and automatically triggering notifications through the Notification Service when tasks are due. It provides a REST API for task creation and uses scheduled jobs to monitor and notify about upcoming or overdue tasks.

## ✨ Features

- **Task Management**: Create and store tasks with titles, email addresses, and due dates
- **Automated Notifications**: Scheduled job checks for due tasks every 60 seconds
- **Service Discovery**: Integrates with Eureka for service registration and discovery
- **Centralized Configuration**: Uses Spring Cloud Config Server for configuration management
- **Inter-Service Communication**: Uses OpenFeign client to communicate with the Notification Service
- **In-Memory Database**: H2 database for development and testing
- **JPA Integration**: Hibernate ORM for data persistence

## 🏗️ Architecture

This microservice is part of a distributed system and communicates with:

- **Config Server** (`service-main`): Centralized configuration management
- **Eureka Server** (`service-main`): Service discovery and registration
- **Notification Service** (`service-notification`): Sending email notifications

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Cloud 2025.0.0**
- **Spring Data JPA**
- **H2 Database**
- **OpenFeign** - Declarative REST client
- **Lombok** - Reduce boilerplate code
- **Netflix Eureka Client** - Service discovery
- **Maven** - Build and dependency management

## 📦 Dependencies

Key dependencies include:

- `spring-boot-starter-web` - REST API support
- `spring-boot-starter-data-jpa` - JPA/Hibernate support
- `spring-cloud-starter-config` - Config server client
- `spring-cloud-starter-netflix-eureka-client` - Service discovery
- `spring-cloud-starter-openfeign` - Feign HTTP client
- `h2` - In-memory database
- `lombok` - Code generation

## 🔧 Configuration

### Application Properties

The service runs on port **8081** and connects to:

- **Config Server**: `http://service-main:8888/config`
- **Eureka Server**: `http://service-main:8888/eureka`
- **Database**: H2 in-memory (`jdbc:h2:mem:testdb`)

### Environment Variables

The service can be configured through Spring Cloud Config Server. Check the `service-tasks.properties` file in the config server repository.

## 🚦 API Endpoints

### Create Task

```http
POST /tasks
Content-Type: application/json

{
  "title": "Complete project documentation",
  "email": "user@example.com",
  "dueDate": "2025-10-27T14:30:00",
  "notified": false
}
```

**Response:**
```json
{
  "id": 1,
  "title": "Complete project documentation",
  "email": "user@example.com",
  "dueDate": "2025-10-27T14:30:00",
  "notified": false
}
```

## ⏰ Scheduled Jobs

### Task Notification Checker

- **Frequency**: Every 60 seconds
- **Function**: Checks for tasks that are due and haven't been notified
- **Action**: Sends notification requests to the Notification Service via Feign client

## 🏃 Running the Service

### Prerequisites

- Java 21 or higher
- Maven 3.6+
- Running Config Server (service-main)
- Running Eureka Server (service-main)

### Local Development

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### Docker

```bash
# Build Docker image
docker build -t service-tasks .

# Run container
docker run -p 8081:8081 service-tasks
```

### Docker Compose

If using the project's docker-compose setup:

```bash
docker-compose up service-tasks
```

## 🗄️ Database

### H2 Console

Access the H2 database console at:
- **URL**: `http://localhost:8081/h2-console`
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (empty)

### Schema

**Task Table:**
| Column | Type | Description |
|--------|------|-------------|
| id | BIGINT | Primary key (auto-increment) |
| title | VARCHAR | Task title |
| email | VARCHAR | Email for notification |
| due_date | TIMESTAMP | When the task is due |
| notified | BOOLEAN | Whether notification was sent |

## 📁 Project Structure

```
service.tasks/
├── src/
│   ├── main/
│   │   ├── java/com/igorcalabraro/service/tasks/
│   │   │   ├── Application.java                  # Main application class
│   │   │   ├── TasksController.java              # REST controller
│   │   │   ├── TasksService.java                 # Business logic
│   │   │   ├── TasksRepository.java              # JPA repository
│   │   │   ├── TasksEntity.java                  # JPA entity
│   │   │   ├── TasksRequest.java                 # DTO for requests
│   │   │   ├── TasksNotificationSchedule.java    # Scheduled jobs
│   │   │   ├── NotificationClient.java           # Feign client
│   │   │   └── NotificationRequest.java          # DTO for notifications
│   │   └── resources/
│   │       └── application.properties             # Configuration
│   └── test/
├── Dockerfile                                     # Docker configuration
└── pom.xml                                        # Maven configuration
```

## 🔄 Service Communication Flow

1. **Task Creation**: User creates a task via POST /tasks endpoint
2. **Storage**: Task is persisted in H2 database with `notified = false`
3. **Scheduled Check**: Every 60 seconds, the scheduler checks for due tasks
4. **Notification**: When a task is due and not notified, the service:
   - Calls Notification Service via Feign client
   - Marks the task as notified (`notified = true`)

## 🔐 Service Discovery

The service registers itself with Eureka Server as `service-tasks` and discovers other services (like `service-notification`) through Eureka for inter-service communication.

## 🐛 Health Check

The service provides Spring Boot Actuator endpoints (if enabled) for monitoring and health checks.

## 📝 Development Notes

- Uses Lombok annotations (@Getter, @Setter, @AllArgsConstructor, @NoArgsConstructor)
- JPA entities are automatically managed by Hibernate
- Feign client handles service-to-service HTTP communication
- Scheduled tasks run on a separate thread pool

## 🚀 Future Enhancements

- [ ] Add task update and delete endpoints
- [ ] Implement task listing with pagination
- [ ] Add task completion status
- [ ] Support for recurring tasks
- [ ] Configurable notification timing
- [ ] Task priority levels
- [ ] User authentication and authorization
- [ ] PostgreSQL/MySQL support for production
- [ ] Metrics and monitoring with Micrometer
- [ ] API documentation with Swagger/OpenAPI

## 📄 License

This project is part of a Spring Boot microservices demonstration.

## 👤 Author

Igor Calabraro

## 🤝 Related Services

- [service.main](../service.main) - Config Server & Eureka Server
- [service.notification](../service.notification) - Notification Service

---

Built with ☕ and Spring Boot

