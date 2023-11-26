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
git clone https://github.com/farneser/task-tracker-scheduler
cd task-tracker-scheduler
./mvnw clean package
```

## Running the Service

Use this service in stack with the other [Task Tracker microservices](https://github.com/farneser/task-tracker)

## Docker

Service images are available on [Docker Hub](https://hub.docker.com/r/farneser/task-tracker-scheduler)

## Environment Variables

| Variable Name     | Default value | Description                                     |
|-------------------|---------------|-------------------------------------------------|
| POSTGRES_HOST     | localhost     | PostgreSQL database host                        |
| POSTGRES_PORT     | 5432          | PostgreSQL database port                        |
| POSTGRES_DB       | task-tracker  | PostgreSQL database name                        |
| POSTGRES_USERNAME | postgres      | PostgreSQL database username                    |
| POSTGRES_PASSWORD | postgres      | PostgreSQL database password                    |
| RABBITMQ_HOST     | localhost     | RabbitMQ host                                   |
| RABBITMQ_PORT     | 5672          | RabbitMQ port                                   |
| RABBITMQ_USERNAME | rabbitmq      | RabbitMQ username                               |
| RABBITMQ_PASSWORD | rabbitmq      | RabbitMQ password                               |
| SCHEDULER_CRON    | 0 0 0 * * *   | Scheduler cron expression (default at midnight) |
| LOG_LEVEL         | INFO          | Spring application logging level                |
