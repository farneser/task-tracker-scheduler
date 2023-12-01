package dev.farneser.tasktracker.scheduler.config;

import com.github.dockerjava.api.model.ExposedPort
import com.github.dockerjava.api.model.HostConfig
import com.github.dockerjava.api.model.PortBinding
import com.github.dockerjava.api.model.Ports
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
                .withExposedPorts(5432)
                .withCreateContainerCmdModifier { cmd ->
                    cmd.withHostConfig(
                        HostConfig().withPortBindings(
                            PortBinding(
                                Ports.Binding.bindPort(15432),
                                ExposedPort(5432)
                            )
                        )
                    )
                }

        pgContainer.start();

        val dataSource = DriverManagerDataSource()

        dataSource.setDriverClassName("org.postgresql.Driver")
        dataSource.url = pgContainer.getJdbcUrl()
        dataSource.username = pgContainer.getUsername()
        dataSource.password = pgContainer.getPassword()

        return dataSource
    }
}

