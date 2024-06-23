package dev.farneser.tasktracker.scheduler.config

import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMqConfig {

    @Bean
    fun getStatisticsQueue(): Queue {
        return Queue(QueueType.GET_STATISTICS)
    }
}