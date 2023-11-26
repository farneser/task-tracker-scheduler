package dev.farneser.tasktracker.scheduler.services.messages

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class MessageService(private val rabbitTemplate: RabbitTemplate) {
    companion object {
        val log: Logger = LoggerFactory.getLogger(MessageService::class.java)
    }

    fun sendScheduledMessage(message: String) {
        log.debug("Sending message to scheduled queue at ${System.currentTimeMillis()} : $message")

        rabbitTemplate.convertAndSend(QueueType.SCHEDULED.toString(), message)
    }
}
