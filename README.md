# Task Tracker Scheduler Service

The Task Tracker Scheduler Service is a microservice designed to handle scheduled tasks, leveraging Spring Boot with
Kotlin, JPA for data persistence, and RabbitMQ for efficient message queuing. This service focuses on collecting
statistics related to tasks and sending them to RabbitMQ for further processing.

## Features

* Task Scheduling: Utilizes cron expressions to schedule recurring tasks efficiently.
* Message Queue Integration: Utilizes RabbitMQ to send collected statistics as messages for asynchronous processing.
* Spring Boot with Kotlin: Employs the power of Spring Boot framework and Kotlin language to build a robust and concise
  microservice.

## Prerequisites

Make sure you have the following installed:

* Java (JDK 17 or higher)
* Kotlin
* Postgres
* RabbitMQ

## Building the Service

To build the service, run the following commands:

```bash
./mvnw clean package
```

## Running the Service

To run the service, run the following commands:

```bash
./mvnw spring-boot:run
```

## Running the Service with Docker

Use this service in stack with the other [Task Tracker microservices](https://github.com/farneser/task-tracker)

## Running the Service with Docker Compose

Get more on stack usage in the [Task Tracker](https://github.com/farneser/task-tracker) repository

```yml
version: "3"

services:
  scheduler:
    image: farneser/task-tracker-scheduler:latest
    container_name: scheduler-container
    environment:
      POSTGRES_HOST: postgres
      POSTGRES_PORT: 5432
      POSTGRES_DB: task-tracker
      POSTGRES_USERNAME: postgres
      POSTGRES_PASSWORD: postgres
      RABBITMQ_HOST: rabbitmq
      RABBITMQ_PORT: 5672
      RABBITMQ_USERNAME: rabbitmq
      RABBITMQ_PASSWORD: rabbitmq
      SCHEDULER_CRON: "0 0 0 * * *" # every day at midnight
      LOG_LEVEL: INFO
```

## Docker

Service images are available on [Docker Hub](https://hub.docker.com/r/farneser/task-tracker-scheduler)

## Environment Variables

### Application

| Parameter                         | Default value   | Description                                     |
|-----------------------------------|-----------------|-------------------------------------------------|
| SCHEDULER_CRON                    | `0 0 0 * * *`   | Scheduler cron expression (default at midnight) |
| SCHEDULER_LOG_LEVEL               | `INFO`          | Spring application logging level                |
| SCHEDULER_TIMEZONE                | `Europe/Minsk ` | Scheduler timezone                              |
| SCHEDULER_ARCHIVED_COLUMN_ENABLED | `false`         | Scheduler statistics archived column enabled    |

### Postgres

| Parameter         | Default value  | Description                                    |
|-------------------|----------------|------------------------------------------------|
| POSTGRES_HOST     | `localhost`    | PostgreSQL database server IP address          |
| POSTGRES_PORT     | `5432`         | PostgreSQL database server port                |
| POSTGRES_DB       | `task-tracker` | PostgreSQL database name                       |
| POSTGRES_USERNAME | `postgres`     | Username for connecting to PostgreSQL database |
| POSTGRES_PASSWORD | `postgres`     | Password for connecting to PostgreSQL database |

### RabbitMQ

| Parameter         | Default value | Description                         |
|-------------------|---------------|-------------------------------------|
| RABBITMQ_HOST     | `localhost`   | RabbitMQ server host                |
| RABBITMQ_PORT     | `5672`        | RabbitMQ server port                |
| RABBITMQ_USERNAME | `rabbitmq`    | Username for connecting to RabbitMQ |
| RABBITMQ_PASSWORD | `rabbitmq`    | Password for connecting to RabbitMQ |
