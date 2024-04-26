package dev.farneser.tasktracker.scheduler.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.testcontainers.containers.PostgreSQLContainer
import javax.sql.DataSource

@Configuration
class TestContainerConfig {
    @Bean
    fun dataSource(): DataSource {
        val pgContainer =
            PostgreSQLContainer("postgres:latest")
                .withDatabaseName("task-tracker")
                .withUsername("postgres")
                .withPassword("postgres")

        pgContainer.start()

        val dataSource = DriverManagerDataSource()

        dataSource.setDriverClassName("org.postgresql.Driver")
        dataSource.url = pgContainer.jdbcUrl
        dataSource.username = pgContainer.username
        dataSource.password = pgContainer.password

        return dataSource
    }
}

