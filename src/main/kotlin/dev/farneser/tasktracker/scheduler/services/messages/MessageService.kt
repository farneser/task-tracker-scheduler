package dev.farneser.tasktracker.scheduler.services.messages

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class MessageService(private val rabbitTemplate: RabbitTemplate) {
    fun sendScheduledMessage(message: String) {
        rabbitTemplate.convertAndSend(QueueType.SCHEDULED.toString(), message)
    }
}
